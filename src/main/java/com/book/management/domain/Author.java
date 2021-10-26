package com.book.management.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NonNull
    private String givenName;

    @Column(length = 50)
    @NonNull
    private String lastName;

    @Column(length = 50)
    @NonNull
    private String DOB;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String givenName, String lastName, String DOB) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.DOB = DOB;
    }


    public void addBook(Book book){
        books.add(book);
        book.setAuthor(this);
    }

    @NonNull
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(@NonNull String givenName) {
        this.givenName = givenName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getDOB() {
        return DOB;
    }

    public void setDOB(@NonNull String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return givenName + " " + lastName;
    }
}
