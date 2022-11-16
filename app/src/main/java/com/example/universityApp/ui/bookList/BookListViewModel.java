package com.example.universityApp.ui.bookList;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.FavBook;
import com.example.universityApp.repositories.favBookRepo.FavBooksRepository;
import com.example.universityApp.ui.UniversityApp;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.bookRepo.BookRepository;
import com.example.universityApp.retrofit.BookServiceController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class BookListViewModel extends ViewModel {
    private ArrayList<Book> books = new ArrayList<>();
    private final BookServiceController bookServiceController;

    private final BookRepository repository;
    private final FavBooksRepository favsRepository;
    private final Executor executor;

    public BookListViewModel(BookRepository repository, FavBooksRepository favsRepository) {
        this.executor = UniversityApp.getAppExecutor();
        this.repository = repository;
        this.bookServiceController = new BookServiceController();
        this.favsRepository = favsRepository;
        fetchBooks();
    }

    private void fetchBooks() {
        executor.execute(() -> {
            books = new ArrayList<>(repository.getAll());
        });
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void fetchAndInsertBooksFromRepo() {
        executor.execute(() -> {
            books = bookServiceController.getBooks();
            books.forEach(repository::insert);
        });
    }

    public void addFavBook(long bookId, long userId) {
        executor.execute(() -> {
            try {
                favsRepository.insert(new FavBook(userId, bookId));
            } catch (SQLiteConstraintException e) {
                Log.e("BookVM", "This fav book already exists ", e);
            }
        });
    }
}
