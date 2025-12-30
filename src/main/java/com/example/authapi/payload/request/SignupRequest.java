package com.example.authapi.payload.request;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(description = "Username (3-20 characters)", example = "john_doe")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User roles (admin, mod, user). Defaults to 'user' if not specified",
            example = "[\"user\", \"mod\"]")
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    @Schema(description = "Password (6-40 characters)", example = "securePassword123")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
