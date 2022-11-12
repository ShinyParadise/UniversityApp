package com.example.universityApp.ui.bookList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.databinding.BookListItemBinding;
import com.example.universityApp.dto.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private ArrayList<Book> books = new ArrayList<>();

    private ClickListener clickListener;

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BookListItemBinding binding = BookListItemBinding.inflate(inflater);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.setBook(book);

        // Normal click
        holder.binding.bookListItemView.setOnClickListener(v -> clickListener.onClick(book));
        // Long click
        holder.binding.bookListItemView.setOnLongClickListener(v -> {
            clickListener.onLongClick(book);
            return false;
        });
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public BookListItemBinding binding;

        public ViewHolder(@NonNull BookListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBook(@NonNull Book book) {
            binding.bookListItemName.setText(book.getName());
            binding.bookListItemAuthor.setText(book.getAuthor());
        }
    }
}
