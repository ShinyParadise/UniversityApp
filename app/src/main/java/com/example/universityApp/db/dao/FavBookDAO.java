package com.example.universityApp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.universityApp.db.models.FavBook;

import java.util.List;

@Dao
public interface FavBookDAO {
    @Query("SELECT * FROM favorite_books")
    List<FavBook> getAll();

    @Query("SELECT * FROM favorite_books WHERE user_id = :user_id")
    List<FavBook> getAllByUser(long user_id);

    @Insert
    void insert(FavBook book);

    @Update
    void update(FavBook book);

    @Delete
    void delete(FavBook book);
}
