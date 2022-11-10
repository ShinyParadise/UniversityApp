package com.example.universityApp.ui.selectedBook;

import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.Book;

public class SelectedBookViewModel extends ViewModel {
    private Book selectedBook;

    public SelectedBookViewModel() {

    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
}
