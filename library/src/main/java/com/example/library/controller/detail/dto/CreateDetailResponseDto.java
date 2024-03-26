package com.example.library.controller.detail.dto;

public class CreateDetailResponseDto {
    private long id;
    private String genre;
    private String summary;
    private String coverImageURL;
    private  long bookId;

    public CreateDetailResponseDto(long id, String genre, String summary, String coverImageURL, long bookId) {
        this.id = id;
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
