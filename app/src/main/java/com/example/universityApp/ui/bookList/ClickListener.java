package com.example.universityApp.ui.bookList;

import com.example.universityApp.dto.Book;

public interface ClickListener {
    void onClick(Book book);
    void onLongClick(Book book);
}
