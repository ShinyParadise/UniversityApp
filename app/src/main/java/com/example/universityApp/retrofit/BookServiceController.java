package com.example.universityApp.retrofit;

import androidx.annotation.NonNull;

import com.example.universityApp.UniversityApp;
import com.example.universityApp.dto.Book;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookServiceController implements Callback<ArrayList<Book>> {
    private ArrayList<Book> books = new ArrayList<>();

    private final BookService bookService;

    public static final String TAG = "BookServiceController";
    public static final String BASE_URL = "https://raw.githubusercontent.com/";

    public BookServiceController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookService = retrofit.create(BookService.class);
        fetch();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    private void fetch() {
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
        call.cancel();
        t.printStackTrace();
    }
}
