package com.example.library.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFound extends RuntimeException {

    private ReviewNotFound(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        ReviewNotFound exception = new ReviewNotFound(String.format("Review with id: %s not found.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
