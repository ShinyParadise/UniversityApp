package com.example.universityApp.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "favorite_books",
        primaryKeys = { "user_id", "book_id" },
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
                @ForeignKey(entity = Book.class, parentColumns = "id", childColumns = "book_id"),
        }
)
public class FavBook {
    @ColumnInfo(name = "user_id", index = true)
    public final long user_id;

    @ColumnInfo(name = "book_id", index = true)
    public final long book_id;

    public FavBook(long user_id, long book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }
}
