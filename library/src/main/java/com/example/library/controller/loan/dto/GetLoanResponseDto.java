package com.example.library.controller.loan.dto;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.user.dto.GetUserDto;

import java.time.LocalDate;

public class GetLoanResponseDto {
    private long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private GetUserDto userId;
    private GetBookDto bookId;

    public GetLoanResponseDto(long id, LocalDate loanDate, LocalDate dueDate, GetUserDto userId, GetBookDto bookId) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public GetUserDto getUserId() {
        return userId;
    }

    public void setUserId(GetUserDto userId) {
        this.userId = userId;
    }

    public GetBookDto getBookId() {
        return bookId;
    }

    public void setBookId(GetBookDto bookId) {
        this.bookId = bookId;
    }
}
