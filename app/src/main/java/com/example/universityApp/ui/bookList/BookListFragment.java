package com.example.universityApp.ui.bookList;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.databinding.FragmentBookListBinding;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.bookRepo.BookRepositoryImpl;

public class BookListFragment extends Fragment {
    private FragmentBookListBinding binding;
    private BooksAdapter adapter;
    private BookListViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences prefs = requireActivity().getSharedPreferences(
                "com.example.universityApp",
                MODE_PRIVATE
        );

        initViewModel();

        if (prefs.getBoolean("wasLogged", true)) {
            Handler handler = new Handler();
            handler.postDelayed(() -> viewModel.fetchAndInsertBooksFromRepo(), 1000);
            prefs.edit().putBoolean("wasLogged", false).apply();
        }

        initRecyclerView(root, viewModel);

        return root;
    }

    private void initViewModel() {
        BookDAO bookDAO = AppDatabase.getDatabase(requireContext()).bookDAO();
        viewModel = new BookListViewModel(new BookRepositoryImpl(bookDAO));
    }

    private void initRecyclerView(View root, @NonNull BookListViewModel viewModel) {
        adapter = new BooksAdapter();

        Handler handler = new Handler();
        handler.postDelayed(() -> adapter.setBooks(viewModel.getBooks()), 100);

        adapter.setClickListener(new ClickListener() {
            @Override
            public void onClick(Book book) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Book", book);

                Navigation.findNavController(root)
                        .navigate(R.id.action_navigation_book_list_to_navigation_selected_book, bundle);
            }

            @Override
            public void onLongClick(Book book) {
                Toast.makeText(requireContext(), "Long press", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView booksRecyclerView = binding.recyclerViewBooks;
        booksRecyclerView.setAdapter(adapter);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
