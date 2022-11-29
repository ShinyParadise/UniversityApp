package com.example.universityApp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.dto.Book;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private ArrayList<Book> books;

    public MainAdapter(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.setBook(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}

class MainViewHolder extends RecyclerView.ViewHolder {
    private final TextView authorView;
    private final TextView titleView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.title);
        authorView = itemView.findViewById(R.id.author);
    }

    public void setBook(@NonNull Book book) {
        titleView.setText(book.getName());
        authorView.setText(book.getAuthor());
    }
}
