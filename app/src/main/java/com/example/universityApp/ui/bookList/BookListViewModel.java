package com.example.universityApp.ui.bookList;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.Book;
import com.example.universityApp.dto.FavBook;
import com.example.universityApp.repositories.bookRepo.BookRepository;
import com.example.universityApp.repositories.favBookRepo.FavBooksRepository;
import com.example.universityApp.retrofit.BookService;
import com.example.universityApp.ui.UniversityApp;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListViewModel extends ViewModel {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private final BookService bookService;
    
    public MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>(new ArrayList<>());

    private final BookRepository repository;
    private final FavBooksRepository favsRepository;
    private final Executor executor;
    private final String TAG = "BookVM";

    public BookListViewModel(BookRepository repository, FavBooksRepository favsRepository) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookService = retrofit.create(BookService.class);
        
        this.executor = UniversityApp.getAppExecutor();
        this.repository = repository;
        this.favsRepository = favsRepository;
        fetchBooks();
    }

    private void fetchBooks() {
        executor.execute(() -> {
            books.postValue(new ArrayList<>(repository.getAll()));
        });
    }

    public ArrayList<Book> getBooks() {
        return books.getValue();
    }

    public void fetchAndInsertBooksFromRepo() {
        Call<ArrayList<Book>> fetchedBooks = bookService.getBooks();
        fetchedBooks.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(
                    @NonNull Call<ArrayList<Book>> call,
                    @NonNull Response<ArrayList<Book>> response
            ) {
                if (response.isSuccessful()) {
                    ArrayList<Book> fetched = response.body();
                    if (fetched != null) {
                        books.setValue(fetched);
                        executor.execute(() -> {
                            fetched.forEach(repository::insert);
                        });
                    } else {
                        Log.e(TAG, "Null response");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Book>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addFavBook(long bookId, long userId) {
        executor.execute(() -> {
            try {
                favsRepository.insert(new FavBook(userId, bookId));
                books.postValue(new ArrayList<>(repository.getAll()));
            } catch (SQLiteConstraintException e) {
                Log.e("BookVM", "This fav book already exists ", e);
            }
        });
    }
}
