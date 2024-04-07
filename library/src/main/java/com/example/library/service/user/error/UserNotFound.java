package com.example.library.service.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound extends RuntimeException {

    private UserNotFound(String message) {
        super(message);
    }

    public static ResponseStatusException createWithId(long id) {
        UserNotFound exception = new UserNotFound(String.format("User with id: %s not found.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    public static ResponseStatusException createWithUsername(String username) {
        UserNotFound exception = new UserNotFound(String.format("User with username: %s not found.", username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
