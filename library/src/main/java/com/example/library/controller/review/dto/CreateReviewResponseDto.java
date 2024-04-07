package com.example.library.controller.review.dto;

import java.time.LocalDate;

public class CreateReviewResponseDto {
    private long id;
    private LocalDate reviewDate;
    private int rating;
    private String comment;
    private long userId;
    private long bookId;

    public CreateReviewResponseDto(long id, LocalDate reviewDate, int rating, String comment, long userId, long bookId) {
        this.id = id;
        this.reviewDate = reviewDate;
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

    public LocalDate getReturnDate() {
        return reviewDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.reviewDate = returnDate;
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