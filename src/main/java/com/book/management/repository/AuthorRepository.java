package com.book.management.repository;

import com.book.management.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findAuthorByGivenNameAndLastName (String givenName, String lastName);
}
