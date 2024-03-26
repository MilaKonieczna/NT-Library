package com.example.library.controller.detail.dto;


import com.example.library.controller.book.dto.GetBookDto;

public class GetDetailDto {
    private long id;
    private String genre;
    private String summary;
    private String coverImageURL;
    private GetBookDto bookId;

    public GetDetailDto(long id, String genre, String summary, String coverImageURL, GetBookDto bookId) {
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

    public GetBookDto getBookId() {
        return bookId;
    }

    public void setBookId(GetBookDto bookId) {
        this.bookId = bookId;
    }
}
