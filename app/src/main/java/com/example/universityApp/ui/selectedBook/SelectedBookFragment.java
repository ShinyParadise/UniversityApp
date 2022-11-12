package com.example.universityApp.ui.selectedBook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.universityApp.databinding.FragmentSelectedBookBinding;
import com.example.universityApp.dto.Book;

public class SelectedBookFragment extends Fragment {
    private FragmentSelectedBookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SelectedBookViewModel selectedBookViewModel =
                new ViewModelProvider(this).get(SelectedBookViewModel.class);

        binding = FragmentSelectedBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        Book book = null;

        if (bundle != null) {
            book = (Book) bundle.getSerializable("Book");
        }

        if (book != null) {
            selectedBookViewModel.setSelectedBook(book);
            updateViewsWithBook(book);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    private void updateViewsWithBook(@NonNull Book book) {
        binding.sbHeader.setText(book.getName());
        binding.sbAuthor.setText("Автор: " + book.getAuthor());
        binding.sbGenre.setText("Жанр: " + book.getGenre());
        binding.sbYear.setText("Год создания: " + book.getPublicationDate());
        binding.sbRating.setText("Рейтинг: " + book.getRating());
    }
}
