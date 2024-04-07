package com.example.library.controller.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public class CreateLoanDto {
    @NotNull
    @Schema(name = "userId", example = "2")
    private Long userId;
    @NotNull
    @Schema(name = "bookId", example = "2")
    private Long bookId;

    public CreateLoanDto(long userId, long bookId) {
        this.userId = userId;
        this.bookId = bookId;
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
