package com.example.library.controller.book.dto.detail;

public class UpdateDetailResponseDto{
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private int availableCopies;
    private DetailDto details;

    public UpdateDetailResponseDto(long id, String isbn, String title, String author, String publisher, int yearPublished, int availableCopies, DetailDto details) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.availableCopies = availableCopies;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public DetailDto getDetails() {
        return details;
    }

    public void setDetails(DetailDto details) {
        this.details = details;
    }
}