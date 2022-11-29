package com.example.universityApp.dto;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Book implements Serializable {
    private transient long id;

    private String author;

    private String genre;

    private String name;

    private String publicationDate;

    private int rating;

    public Book(String name, String author) {
        this.author = author;
        this.name = name;
    }

    public Book(String name, String author, String genre, String publicationDate, int rating) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.rating = rating;
    }

    public Book(long id, String name, String author, String genre, String publicationDate, int rating) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", rating=" + rating +
                '}';
    }
}
