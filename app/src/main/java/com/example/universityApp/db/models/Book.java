package com.example.universityApp.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
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

    public Book(String author, String name, String genre, String publicationDate, int rating) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.rating = rating;
    }
}
