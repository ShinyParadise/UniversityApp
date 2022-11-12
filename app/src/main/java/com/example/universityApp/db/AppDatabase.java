package com.example.universityApp.db;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.db.dao.FavBookDAO;
import com.example.universityApp.db.dao.UserDAO;
import com.example.universityApp.db.models.Book;
import com.example.universityApp.db.models.FavBook;
import com.example.universityApp.db.models.User;

@Database(
        entities = {User.class, Book.class, FavBook.class},
        version = 2,
        autoMigrations = { @AutoMigration(from = 1, to = 2) }
)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String dbName = "book.db";

    public abstract BookDAO bookDAO();
    public abstract FavBookDAO favBookDAO();
    public abstract UserDAO userDAO();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName)
                    .build();
        }
        return INSTANCE;
    }
}
