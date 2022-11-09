package com.example.universityApp.ui.bookList;

import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.Book;
import com.example.universityApp.retrofit.BookServiceController;

import java.util.ArrayList;

public class BookListViewModel extends ViewModel {
    private ArrayList<Book> books;
    private final BookServiceController bookServiceController;

    public BookListViewModel() {
        books = new ArrayList<>();
        bookServiceController = new BookServiceController();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void fetchBooks() {
        books = bookServiceController.fetchBooks();
    }
}
