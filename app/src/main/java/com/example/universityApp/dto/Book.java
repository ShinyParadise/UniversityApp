package com.example.universityApp.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable {
    private transient Long id;

    @SerializedName("Author")
    @Expose
    private String author;

    @SerializedName("Genre")
    @Expose
    private String genre;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("PublicationDate")
    @Expose
    private String publicationDate;

    @SerializedName("rating")
    @Expose
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

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
