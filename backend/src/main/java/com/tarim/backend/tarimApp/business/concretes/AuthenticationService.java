package com.tarim.backend.tarimApp.business.concretes;

import com.tarim.backend.tarimApp.business.dto.request.Authentication.ForgotPasswordRequest;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.ResetPasswordRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.AuthenticationRequest;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.RegisterRequest;
import com.tarim.backend.tarimApp.business.dto.response.authentication.AuthenticationResponse;
import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.core.enums.Role;
import com.tarim.backend.tarimApp.dataAccess.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;
    private final VerificationCodeService verificationCodeService;


    public AuthenticationResponse register(RegisterRequest request) {
        var user =  User.builder()

                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .image(request.getImage())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user =  userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }
    public void forgotPassword(ForgotPasswordRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String verificationCode = verificationCodeService.generateVerificationCode(); // Yeni ekleme
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10); // Kodun geçerlilik süresi 10 dakika

        user.setVerificationCode(verificationCode);
        user.setCodeExpirationTime(expirationTime);
        userRepository.save(user);

        sendVerificationCodeEmail(user.getEmail(), verificationCode);
    }

    public void resetPassword(ResetPasswordRequest request) {
        var user = userRepository.findByVerificationCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Invalid code"));

        if (user.getCodeExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification code has expired");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setVerificationCode(null);
        user.setCodeExpirationTime(null);
        userRepository.save(user);
    }

    private void sendVerificationCodeEmail(String email, String verificationCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Password Reset Verification Code");
            helper.setText("Your verification code is: " + verificationCode);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}