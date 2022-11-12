package com.example.universityApp.dto;

import java.io.Serializable;

public class FavBook implements Serializable {
    private long userId;
    private long bookId;

    public FavBook(long userId, long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
