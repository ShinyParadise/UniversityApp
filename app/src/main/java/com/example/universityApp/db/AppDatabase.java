package com.example.universityApp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.db.models.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String dbName = "book.db";

    public abstract BookDAO bookDAO();

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName)
                    .build();
        }
        return INSTANCE;
    }
}