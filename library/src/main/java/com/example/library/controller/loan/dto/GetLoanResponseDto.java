package com.example.library.controller.loan.dto;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.user.dto.GetUserDto;

import java.sql.Date;

public class GetLoanResponseDto {
    private long id;
    private Date loanDate;
    private Date dueDate;
    private GetUserDto userId;
    private GetBookDto bookId;

    public GetLoanResponseDto(long id, Date loanDate, Date dueDate, GetUserDto userId, GetBookDto bookId) {
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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
