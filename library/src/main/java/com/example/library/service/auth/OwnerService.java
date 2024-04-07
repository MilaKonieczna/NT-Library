package com.example.library.service.auth;

import com.example.library.commonTypes.UserRole;
import com.example.library.infrastructure.entity.AuthEntity;
import com.example.library.infrastructure.repository.AuthRepository;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.security.core.Authentication;

public abstract class OwnerService {
    protected final AuthRepository authRepository;

    protected OwnerService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean isOwner(String username, Long userId) {
        if (userId == null || username == null) {
            return false;
        }

        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(() -> UserNotFound.createWithUsername(username));
        return userId == authEntity.getUser().getId();
    }
    public AuthInfo getAuthInfo(Authentication authentication) {
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        UserRole role = null;
        var username = authentication.getName();
        var authorities = authentication.getAuthorities().stream().toList();
        if(!authorities.isEmpty()) {
            role = UserRole.valueOf(authorities.get(0).getAuthority());
        }
        return new AuthInfo(role, username);
    }

    public record AuthInfo(UserRole role, String username) {
    }
    protected boolean isOwnerOrAdmin(Long userId, AuthInfo auth) {
        if(userId == null || auth == null){
            return false;}
        return auth.role() != null && auth.role().equals(UserRole.ROLE_ADMIN) || isOwner(auth.username(), userId);
    }

}