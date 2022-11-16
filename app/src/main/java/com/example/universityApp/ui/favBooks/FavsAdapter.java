package com.example.universityApp.ui.favBooks;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.databinding.BookListItemBinding;
import com.example.universityApp.dto.Book;

import java.util.ArrayList;

public class FavsAdapter extends RecyclerView.Adapter<FavsAdapter.ViewHolder> {
    private ArrayList<Book> favBooks = new ArrayList<>();

    public void setFavBooks(ArrayList<Book> favBooks) {
        this.favBooks = favBooks;
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
        Book book = favBooks.get(position);
        holder.setBook(book);
    }

    @Override
    public int getItemCount() {
        return favBooks.size();
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
