package com.example.library.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewDoesntExist extends RuntimeException {

    private ReviewDoesntExist(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        ReviewDoesntExist exception = new ReviewDoesntExist(String.format("Review with id: %s doesn't exist.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
