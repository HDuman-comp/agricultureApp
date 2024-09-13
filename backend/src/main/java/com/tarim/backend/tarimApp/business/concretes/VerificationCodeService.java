package com.tarim.backend.tarimApp.business.concretes;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service

public class VerificationCodeService {
    private static final String DIGITS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            code.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return code.toString();
    }
}
