package com.example.universityApp.retrofit;

import com.example.universityApp.dto.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    @GET("Lpirskaya/JsonLab/master/Books2022.json")
    Call<ArrayList<Book>> getBooks();
}
