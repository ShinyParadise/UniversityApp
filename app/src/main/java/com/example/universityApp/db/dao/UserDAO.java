package com.example.universityApp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.universityApp.db.models.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Insert
    Long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
