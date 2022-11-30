package com.example.universityApp.ui.bookList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.Book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit.BookService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    public MutableLiveData<List<Book>> books = new MutableLiveData<>(new LinkedList<>());

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BookService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final BookService service = retrofit.create(BookService.class);

    public MainViewModel() {
        setupApiService();
    }

    private void setupApiService() {
        service.getBooks().enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Book>> call,
                                   @NonNull Response<ArrayList<Book>> response) {
                if (response.isSuccessful()) {
                    books.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Book>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
