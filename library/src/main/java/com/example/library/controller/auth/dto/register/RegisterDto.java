package com.example.library.controller.auth.dto.register;

import com.example.library.commonTypes.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDto {
    @NotBlank(message = "Password must be provided")
    private String password;
    @NotBlank(message = "Username must be provided")
    private String username;
    private UserRole role;
    @NotBlank(message = "Email must be provided")
    @Email
    private String email;
    public RegisterDto(String password, String username, UserRole role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
