package com.book.management.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String genre;

    @NonNull
    private int publicationYear;

    @NonNull
    @Column(length = 10000)
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Book() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(String title, String genre, int publicationYear, String summary) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return title;
    }
}
