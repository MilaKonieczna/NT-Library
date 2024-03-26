package com.example.library.controller.review.dto;

public class CreateReviewResponseDto {
    private long id;
    private int rating;
    private  String comment;
    private long book;
    private long user;

    public CreateReviewResponseDto(long id, int rating, String comment, long book, long user) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.book = book;
        this.user = user;
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

    public long getBook() {
        return book;
    }

    public void setBook(long book) {
        this.book = book;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
