package com.example.universityApp.ui.mainScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.ui.UniversityApp;
import com.example.universityApp.dto.User;
import com.example.universityApp.repositories.userRepo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<List<User>> users = new MutableLiveData<>(new ArrayList<>());
    private final UserRepository repository;
    private final Executor executor;

    public LoginViewModel(@NonNull UserRepository repository) {
        this.repository = repository;
        this.executor = UniversityApp.getAppExecutor();
        fetchUsers();
    }

    public List<User> getUsers() {
        return users.getValue();
    }

    public void fetchUsers() {
        executor.execute(() -> users.postValue(repository.getAll()));
    }

    public void insertUser(User user) {
        executor.execute(() -> {
            repository.insert(user);
            users.postValue(repository.getAll());
        });
    }
}
