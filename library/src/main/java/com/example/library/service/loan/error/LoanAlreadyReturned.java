package com.example.library.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyReturned extends RuntimeException {

    private LoanAlreadyReturned(String message) {
        super(message);
    }

    public static ResponseStatusException create(long id) {
        LoanAlreadyReturned exception = new LoanAlreadyReturned(String.format("Loan with id: %s already returned", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
