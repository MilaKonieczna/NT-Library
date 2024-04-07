package com.example.library.controller.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateReviewDto {
    @NotNull
    @Schema(name = "rating", example = "3")
    private int rating;
    @Schema(name = "comment", example = "amazing story")
    private String comment;
    @NotNull
    @Schema(name = "userId", example = "1")
    private Long userId;
    @NotNull
    @Schema(name = "bookId", example = "2")
    private Long bookId;

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
