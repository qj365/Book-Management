package com.book.management.repository;

import com.book.management.domain.Author;
import com.book.management.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByTitle(String bookTitle);
    List<Book> findBooksByGenre(String genre);
    List<Book> findBookByAuthor(Author author);
}
