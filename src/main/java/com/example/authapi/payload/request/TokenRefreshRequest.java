package com.example.authapi.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    @Schema(description = "Refresh token obtained from signin or previous refresh",
            example = "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
