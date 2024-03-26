package com.example.library.controller.review.dto;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.user.dto.GetUserDto;

public class GetReviewDto {
    private long id;
    private int rating;
    private  String comment;
    private GetUserDto userId;
    private GetBookDto bookId;

    public GetReviewDto(long id, int rating, String comment, GetUserDto userId, GetBookDto bookId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
