package com.example.universityApp.ui.bookList;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.dto.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LifecycleOwner {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private MainAdapter adapter;
    private WearableRecyclerView recyclerView;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.book_list);

        viewModel = new MainViewModel();

        final Observer<List<Book>> booksObserver = books -> {
            adapter.setBooks(new ArrayList<>(books));
        };
        viewModel.books.observe(this, booksObserver);

        setupRecyclerView();
    }


    private void setupRecyclerView() {
        adapter = new MainAdapter(viewModel.books.getValue());

        recyclerView.setLayoutManager(new WearableLinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setCircularScrollingGestureEnabled(true);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
