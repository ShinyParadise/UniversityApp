package com.example.universityApp.ui.mainScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.UniversityApp;
import com.example.universityApp.dto.User;
import com.example.universityApp.repositories.userRepo.UserRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class LoginViewModel extends ViewModel {
    private List<User> users;
    private final UserRepository repository;
    private final Executor executor;

    public LoginViewModel(@NonNull UserRepository repository) {
        this.repository = repository;
        this.executor = UniversityApp.getAppExecutor();
        fetchUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void fetchUsers() {
        executor.execute(() -> {
            users = repository.getAll();
        });
    }

    public void insertUser(User user) {
        executor.execute(() -> {
            repository.insert(user);
        });
    }

    public boolean validateUser(@NonNull User user) {
        return users.contains(user);
    }
}
