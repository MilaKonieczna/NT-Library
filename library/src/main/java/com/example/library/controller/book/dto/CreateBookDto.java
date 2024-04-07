package com.example.library.controller.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBookDto {
    @Size(min = 10, max = 13)
    @NotBlank(message = "Isbn must be provided")
    @NotBlank(message = "Isbn must be provided")
    @Schema(name = "isbn", example = "9780060853969")
    private String isbn;
    @NotBlank(message = "Title must be provided")
    @Schema(name = "title", example = "Good Omens")
    private String title;
    @Schema(name = "author", example = "Neil Gaiman")
    private String author;
    @NotBlank(message = "Author must be provided")
    @Schema(name = "publisher", example = "Gollancz")
    private String publisher;
    @NotNull(message = "Year of publication must be provided")
    @Schema(name = "publicationYear", example = "1990")
    private Integer publicationYear;
    @Min(value = 0)
    @NotNull(message = "Numbers of copies must be provided")
    @Schema(name = "availableCopies", example = "3")
    private Integer availableCopies;

    public CreateBookDto(String isbn, String title, String author, String publisher, Integer publicationYear, Integer availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}