package com.example.library.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExist extends RuntimeException {

    private BookAlreadyExist(String message) {
        super(message);
    }

    public static ResponseStatusException create(String isbn) {
        BookAlreadyExist exception = new BookAlreadyExist(String.format("Book with isbn: %s already exist.", isbn));
        return new ResponseStatusException(HttpStatus.ALREADY_REPORTED, exception.getMessage(), exception);
    }
}
