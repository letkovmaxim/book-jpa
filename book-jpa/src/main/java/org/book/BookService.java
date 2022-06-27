package org.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    public ArrayList<Book> getBookById(long id) {
        if (bookRepository.findById(id).isPresent()) {
            return new ArrayList<Book>(List.of(bookRepository.findById(id).get()));
        }
        return null;
    }

    public ArrayList<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public ArrayList<Book> getBookByDescription(String description) {
        return bookRepository.findByDescription(description);
    }

    public void saveOrUpdate(Book book){
        bookRepository.save(book);
    }

    public void delete(long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        }
    }
}