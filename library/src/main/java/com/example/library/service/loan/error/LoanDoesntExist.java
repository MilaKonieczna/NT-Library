package com.example.library.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanDoesntExist extends RuntimeException {

    private LoanDoesntExist(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        LoanDoesntExist exception = new LoanDoesntExist(String.format("Loan with id: %s doesn't exist.", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
