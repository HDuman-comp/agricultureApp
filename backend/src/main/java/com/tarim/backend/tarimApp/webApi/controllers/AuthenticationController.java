package com.tarim.backend.tarimApp.webApi.controllers;

import com.tarim.backend.tarimApp.business.concretes.AuthenticationService;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.AuthenticationRequest;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.ForgotPasswordRequest;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.RegisterRequest;
import com.tarim.backend.tarimApp.business.dto.request.Authentication.ResetPasswordRequest;
import com.tarim.backend.tarimApp.business.dto.response.authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("Register endpoint reached");
    return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("Authenticate endpoint reached");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authenticationService.forgotPassword(request);
        return ResponseEntity.ok("Password reset email sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        authenticationService.resetPassword(request);
        return ResponseEntity.ok("Password successfully reset.");
    }

}
