package com.example.universityApp.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.universityApp.dto.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookServiceController implements Callback<ArrayList<Book>> {
    private ArrayList<Book> books = new ArrayList<>();

    public static final String TAG = "BookServiceController";
    public static final String BASE_URL = "https://raw.githubusercontent.com/";

    public ArrayList<Book> fetchBooks() {
        fetch();
        return books;
    }

    private void fetch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);

        Call<ArrayList<Book>> fetchedBooks = bookService.getBooks();
        fetchedBooks.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ArrayList<Book>> call, @NonNull Response<ArrayList<Book>> response) {
        if (response.isSuccessful()) {
            books = response.body();
            // Было нужно только для 4 лабы
            /* Log.i(TAG, "onResponse: Successful. Results are:\n");
            if (books != null) {
                books.forEach(book -> Log.i(TAG, book.toString()));
            } else {
                Log.i(TAG, "No books");
            }*/
        }
    }

    @Override
    public void onFailure(@NonNull Call<ArrayList<Book>> call, @NonNull Throwable t) {
        t.printStackTrace();
    }
}
