package com.example.library.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFound extends RuntimeException {

    private LoanNotFound(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        LoanNotFound exception = new LoanNotFound(String.format("Loan with id: %s not found.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
