package com.example.universityApp.ui.favBooks;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.databinding.FragmentFavBooksBinding;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.db.dao.FavBookDAO;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.favBookRepo.FavBooksRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class FavBooksFragment extends Fragment {
    private FavsAdapter adapter;
    private FragmentFavBooksBinding binding;
    private FavBooksViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavBooksBinding.inflate(inflater, container, false);

        SharedPreferences prefs = requireActivity().getSharedPreferences(
                "com.example.universityApp",
                MODE_PRIVATE
        );
        long userId = prefs.getLong("userId", -1);
        initViewModel(userId);

        initRecyclerView(viewModel);

        return binding.getRoot();
    }

    private void initViewModel(long userId) {
        FavBookDAO favBookDAO = AppDatabase.getDatabase(requireContext()).favBookDAO();
        BookDAO bookDAO = AppDatabase.getDatabase(requireContext()).bookDAO();
        viewModel = new FavBooksViewModel(
                new FavBooksRepositoryImpl(favBookDAO, bookDAO),
                userId
        );
    }

    private void initRecyclerView(@NonNull FavBooksViewModel viewModel) {
        adapter = new FavsAdapter();

        final Observer<List<Book>> bookObserver = books -> {
            adapter.setFavBooks(new ArrayList<>(books));
        };
        viewModel.favBooks.observe(getViewLifecycleOwner(), bookObserver);

        RecyclerView favsRecyclerView = binding.fbRecyclerView;
        favsRecyclerView.setAdapter(adapter);
        favsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
