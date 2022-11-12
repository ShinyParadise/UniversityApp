package com.example.universityApp.ui.favBooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.universityApp.databinding.FragmentFavBooksBinding;

public class FavBooksFragment extends Fragment {

    private FragmentFavBooksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavBooksViewModel favBooksViewModel =
                new ViewModelProvider(this).get(FavBooksViewModel.class);

        binding = FragmentFavBooksBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
