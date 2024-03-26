package com.example.library.service.detail.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DetailNotFound extends RuntimeException{

    private DetailNotFound(String message){
        super(message);
    }

    public static ResponseStatusException create(long id){
        DetailNotFound exception = new DetailNotFound(String.format("Detail with id: %s not found.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);}
}
