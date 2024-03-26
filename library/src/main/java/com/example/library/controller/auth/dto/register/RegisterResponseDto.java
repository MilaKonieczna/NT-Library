package com.example.library.controller.auth.dto.register;

import com.example.library.commonTypes.UserRole;

public class RegisterResponseDto {
    private long userId;
    private String username;
    private UserRole role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RegisterResponseDto(long userId, String username, UserRole role) {
        this.username = username;
        this.role = role;
        this.userId = userId;
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
}
