package com.example.library.service.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Forbidden {
    public static ResponseStatusException create(){
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
    }
}
