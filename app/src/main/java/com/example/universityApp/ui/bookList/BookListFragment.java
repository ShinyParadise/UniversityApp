package com.example.universityApp.ui.bookList;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityApp.R;
import com.example.universityApp.databinding.FragmentBookListBinding;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.repositories.BookRepositoryImpl;

public class BookListFragment extends Fragment {
    private FragmentBookListBinding binding;
    private BooksAdapter adapter;
    private BookListViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BookDAO bookDAO = AppDatabase.getDatabase(requireContext()).bookDAO();
        viewModel = new BookListViewModel(new BookRepositoryImpl(bookDAO));

        initiateRecyclerView(root, viewModel);
//        final Handler handler = new Handler();
//        final Runnable r = viewModel::fetchBooks;
//
//        handler.postDelayed(r, 1000);

        return root;
    }

    private void initiateRecyclerView(View root, @NonNull BookListViewModel viewModel) {
        adapter = new BooksAdapter();
        adapter.setClickListener(book -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Book", book);

            Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_book_list_to_navigation_selected_book, bundle);
        });

        final Handler handler = new Handler();
        final Runnable r = () -> {
            viewModel.fetchBooks();
            adapter.setBooks(viewModel.getBooks());
        };
        handler.postDelayed(r, 1000);

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
