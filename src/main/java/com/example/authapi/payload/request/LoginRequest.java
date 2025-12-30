package com.example.authapi.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    @Schema(description = "Username for authentication", example = "john_doe")
    private String username;

    @NotBlank
    @Schema(description = "Password for authentication", example = "securePassword123")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
