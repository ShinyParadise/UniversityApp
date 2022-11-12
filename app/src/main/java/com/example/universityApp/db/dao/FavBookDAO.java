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

    @Insert
    void insert(FavBook book);

    @Update
    void update(FavBook book);

    @Delete
    void delete(FavBook book);
}
