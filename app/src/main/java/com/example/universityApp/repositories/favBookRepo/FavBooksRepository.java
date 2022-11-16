package com.example.universityApp.repositories.favBookRepo;

import com.example.universityApp.dto.Book;
import com.example.universityApp.dto.FavBook;

import java.util.List;

public interface FavBooksRepository {
    List<Book> getAllUserBooks(long userID);
    void insert(FavBook book);
    void update(FavBook book);
    void delete(FavBook book);
}
