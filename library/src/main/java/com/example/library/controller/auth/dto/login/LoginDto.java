package com.example.library.controller.auth.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @Schema(name = "username", example = "admin")
    @NotBlank(message = "Username must be provided")
    private String username;
    @Schema(name = "password", example = "admin")
    @NotBlank(message = "Password must be provided")
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
