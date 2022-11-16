package com.example.universityApp.ui.favBooks;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.favBookRepo.FavBooksRepository;
import com.example.universityApp.ui.UniversityApp;

import java.util.List;
import java.util.concurrent.Executor;

public class FavBooksViewModel extends ViewModel {
    public MutableLiveData<List<Book>> favBooks = new MutableLiveData<>();

    private long userId;

    private final FavBooksRepository repository;
    private final Executor executor;

    public FavBooksViewModel(@NonNull FavBooksRepository repository, long userId) {
        this.repository = repository;
        this.userId = userId;
        executor = UniversityApp.getAppExecutor();
        fetch();
    }

    public void fetch() {
        executor.execute(() -> {
            favBooks.postValue(repository.getAllUserBooks(userId));
        });
    }

}
