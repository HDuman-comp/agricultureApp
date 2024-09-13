package com.tarim.backend.tarimApp.business.dto.request.Authentication;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String newPassword;
    private String code; // Yeni ekleme
}
