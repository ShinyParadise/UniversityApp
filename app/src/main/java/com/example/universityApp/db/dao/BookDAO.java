package com.example.universityApp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.universityApp.db.models.Book;

import java.util.List;

@Dao
public interface BookDAO {
    @Query("SELECT * FROM books")
    List<Book> getAll();

    @Insert
    Long insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);
}
