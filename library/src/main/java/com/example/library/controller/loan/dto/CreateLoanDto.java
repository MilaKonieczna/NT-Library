package com.example.library.controller.loan.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateLoanDto {
    @NotNull
    private Date dueDate;
    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;

    public CreateLoanDto(Date dueDate, long userId, long bookId) {
        this.dueDate = dueDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
