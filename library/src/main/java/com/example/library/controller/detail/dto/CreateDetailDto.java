package com.example.library.controller.detail.dto;

import jakarta.validation.constraints.NotNull;

public class CreateDetailDto {

    private String genre;
    private String summary;
    private String coverImageURL;
    @NotNull
    private  long bookId;

    public CreateDetailDto(String genre, String summary, String coverImageURL, long bookId) {
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
        this.bookId = bookId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
