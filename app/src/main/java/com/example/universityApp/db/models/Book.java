package com.example.universityApp.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "books", foreignKeys = {
        @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "user_id",
        onDelete = ForeignKey.CASCADE)
})
public class Book {
    @PrimaryKey(autoGenerate = true)
    public long id = 0;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "genre")
    public String genre;

    @ColumnInfo(name = "publication_date")
    public String publicationDate;

    @ColumnInfo(name = "rating")
    public int rating;

    @ColumnInfo(name = "is_favorite")
    public boolean is_favorite = false;

    @ColumnInfo(name = "user_id", index = true)
    public long user_id;

    public Book(String author, String name, String genre, String publicationDate, int rating, long user_id) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.user_id = user_id;
    }
}
