package com.example.library.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "details", schema = "library")
public class DetailEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    private BookEntity book;

    @Basic
    @Column(name = "genre", nullable = false)
    private String genre;

    @Basic
    @Column(name = "summary", nullable = false)
    private String summary;

    @Basic
    @Column(name = "coverImageURL", nullable = false)
    private String coverImageURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
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
}
