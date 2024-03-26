package com.example.library.controller.review.dto;

import jakarta.validation.constraints.NotNull;

public class CreateReviewDto {
    @NotNull
    private int rating;
    private  String comment;
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;

    public CreateReviewDto(int rating, String comment, Long bookId, Long userId) {
        this.rating = rating;
        this.comment = comment;
        this.bookId = bookId;
        this.userId = userId;
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
