package org.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BookRepository extends JpaRepository<Book, Long> {
    ArrayList<Book> findByTitle(String title);

    ArrayList<Book> findByDescription(String description);
}