package com.example.universityApp.ui.bookList;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.universityApp.R;
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

public class MainActivity extends Activity {
    private MainAdapter adapter;
    private WearableRecyclerView recyclerView;

    private MutableLiveData<List<Book>> books = new MutableLiveData<>(new LinkedList<>());

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BookService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final BookService service = retrofit.create(BookService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.book_list);

        final Observer<ArrayList<Book>> booksObserver = books -> {
            adapter.setBooks(books);
        };
        //books.observe(this, booksObserver);

        setupApiService();
        setupRecyclerView();
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

    private void setupRecyclerView() {
        adapter = new MainAdapter(books.getValue());

        recyclerView.setLayoutManager(new WearableLinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setCircularScrollingGestureEnabled(true);
    }
}
