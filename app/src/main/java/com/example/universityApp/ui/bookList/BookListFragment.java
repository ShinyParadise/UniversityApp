package com.example.universityApp.ui.bookList;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.databinding.FragmentBookListBinding;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.db.dao.FavBookDAO;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.bookRepo.BookRepositoryImpl;
import com.example.universityApp.repositories.favBookRepo.FavBooksRepositoryImpl;

import java.util.ArrayList;

public class BookListFragment extends Fragment {
    private final static String TAG = "BookListFragment";
    private long userId;

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

        userId = prefs.getLong("userId", -1);

        initViewModel();

        if (prefs.getBoolean("wasLogged", true)) {
            viewModel.fetchAndInsertBooksFromRepo();
            prefs.edit().putBoolean("wasLogged", false).apply();
        }

        initRecyclerView(root, viewModel);

        return root;
    }

    private void initViewModel() {
        BookDAO bookDAO = AppDatabase.getDatabase(requireContext()).bookDAO();
        FavBookDAO favBookDAO = AppDatabase.getDatabase(requireContext()).favBookDAO();
        viewModel = new BookListViewModel(
                new BookRepositoryImpl(bookDAO),
                new FavBooksRepositoryImpl(favBookDAO, bookDAO)
        );
    }

    private void initRecyclerView(View root, @NonNull BookListViewModel viewModel) {
        adapter = new BooksAdapter();

        final Observer<ArrayList<Book>> booksObserver = books -> {
            adapter.setBooks(books);
        };
        viewModel.books.observe(getViewLifecycleOwner(), booksObserver);

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
                showDialog(book);
            }
        });

        RecyclerView booksRecyclerView = binding.recyclerViewBooks;
        booksRecyclerView.setAdapter(adapter);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void showDialog(@NonNull Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(true)
                .setIcon(R.drawable.logo_library)
                .setMessage(book.getName())
                .setTitle("???????????????? ?? ??????????????????")
                .setNegativeButton("????????????", (dialog, which) -> {
                    Log.i(TAG, "Add favorite cancelled");
                })
                .setPositiveButton("?? ??????????????????", ((dialog, which) -> {
                    viewModel.addFavBook(book.getId(), userId);
                    Log.i(TAG, book.toString());
                }));
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
