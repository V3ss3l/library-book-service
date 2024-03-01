package com.library.librarybookservice.controllers;

import com.library.librarybookservice.exceptions.NotFoundException;
import com.library.librarybookservice.models.Book;
import com.library.librarybookservice.repos.BookRepository;
import com.library.librarybookservice.services.BookService;
import com.library.librarybookservice.services.BookServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/library/books")
@Log4j
public class BookController {
    @Autowired
    final BookService service;

    public BookController(BookServiceImpl service) {
        this.service = service;
    }

    @GetMapping( path = "/",
            produces = "application/json"
    )
    public ResponseEntity<List<Book>> getAllBooks() {
        var list = service.getAllEntries();
        if (!list.isEmpty()) return new ResponseEntity<>(list, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @SneakyThrows
    @GetMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        var book = service.getEntryById(id);
        if (book != null) return new ResponseEntity<>(book, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping( path = "/",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        var result = service.create(book);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
