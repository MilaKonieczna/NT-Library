package com.example.library.service.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthNotFound extends RuntimeException{

    private AuthNotFound(String message){
        super(message);
    }

    public static ResponseStatusException create(String username){
        AuthNotFound exception = new AuthNotFound(String.format("User with username: %s not found.", username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);}
}
