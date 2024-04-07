package com.example.library.service.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists extends RuntimeException {

    private UserAlreadyExists(String message) {
        super(message);
    }

    public static ResponseStatusException create(String username) {
        UserAlreadyExists exception = new UserAlreadyExists(String.format("User with username: %s already exists.", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
