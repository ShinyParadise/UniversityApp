package com.example.universityApp.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.dto.Book;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {
    private MainAdapter adapter;
    private WearableRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.book_list);

        List<Book> bookList = new LinkedList<>();
        bookList.add(new Book("Название 1", "Автор 1"));
        bookList.add(new Book("Название 2", "Автор 2"));
        bookList.add(new Book("Название 3", "Автор 3"));
        bookList.add(new Book("Название 4", "Автор 4"));
        bookList.add(new Book("Название 5", "Автор 5"));
        bookList.add(new Book("Название 6", "Автор 7"));

        adapter = new MainAdapter(bookList);

        recyclerView.setLayoutManager(new WearableLinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setCircularScrollingGestureEnabled(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);

    }
}
