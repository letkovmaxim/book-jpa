package org.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    private String info() {
        return "<p>Получить все книги - <b>/books</b></p>" +
                "<p>Найти книгу - <b>/book?id=</i>[ID]</b>, <b>/book?title=[Название]</b> или <b>/book?description=[Описание]</b></p>" +
                "<p>Добавить книгу - <b>/add?title=[Название]&description=[Описание]</b></p>" +
                "<p>Удалить книгу - <b>/delete?id=[ID]</b></p>";
    }

    @GetMapping("/books")
    private List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book")
    private ArrayList<Book> getBook(@RequestParam(value = "id") Optional<Long> id,
                                    @RequestParam(value = "title") Optional<String> title,
                                    @RequestParam(value = "description") Optional<String> description) {
        if (id.isPresent()) {
            return bookService.getBookById(id.get());
        } else if (title.isPresent()) {
            return bookService.getBookByTitle(title.get());
        } else if (description.isPresent()) {
            return bookService.getBookByDescription(description.get());
        }
        return null;
    }

    @GetMapping("/delete")
    private void deleteBook(@RequestParam(value = "id") long id) {
        bookService.delete(id);
    }

    @GetMapping("/add")
    private long saveBook(@RequestParam(value = "title") String title,
                          @RequestParam(value = "description") String description) {
        Book bookToAdd = new Book(title, description);
        bookService.saveOrUpdate(bookToAdd);
        return bookToAdd.getUid();
    }
}