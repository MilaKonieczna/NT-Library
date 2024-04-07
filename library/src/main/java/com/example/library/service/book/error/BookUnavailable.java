package com.example.library.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookUnavailable extends RuntimeException {

    private BookUnavailable(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        BookUnavailable exception = new BookUnavailable(String.format("Book with id: %s is unavailable.", id));
        return new ResponseStatusException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, exception.getMessage(), exception);
    }
}
