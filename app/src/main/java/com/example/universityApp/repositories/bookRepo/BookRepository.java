package com.example.universityApp.repositories.bookRepo;

import com.example.universityApp.dto.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    Book insert(Book book);
    void update(Book book);
    void delete(Book book);
}

