package com.tarim.backend.tarimApp.business.dto.request.Authentication;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String email;
}