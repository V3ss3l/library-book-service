package com.library.librarybookservice.services;

import com.library.librarybookservice.exceptions.NotFoundException;
import com.library.librarybookservice.models.Book;
import com.library.librarybookservice.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private final BookRepository repository;


    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Book> getAllEntries() {
        return repository.findAll();
    }
    @Override
    public Book getEntryById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book by this "+id+" not found in database"));
    }
    @Override
    public Book create(Book obj) {
        return repository.save(obj);
    }
    @Override
    public void delete(Long id) throws NotFoundException{
        repository.findById(id).ifPresent(result -> repository.deleteById(id));
    }
}
