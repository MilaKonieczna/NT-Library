package com.example.library.controller.book.dto.detail;

public class UpdateDetailDto{
    private String genre;
    private String summary;
    private String cover;

    public UpdateDetailDto(String genre, String summary, String cover) {
        this.genre = genre;
        this.summary = summary;
        this.cover = cover;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}