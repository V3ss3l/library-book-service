package com.library.librarybookservice.services;

import com.library.librarybookservice.exceptions.NotFoundException;
import com.library.librarybookservice.models.Book;

import java.util.List;

public interface EntityService<T> {
    public List<T> getAllEntries();

    public T getEntryById(Long id) throws NotFoundException;

    public T create(T obj);

    public void delete(Long id) throws NotFoundException;
}
