package com.example.universityApp.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.universityApp.UniversityApp;
import com.example.universityApp.dto.User;
import com.example.universityApp.repositories.UserRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class LoggedViewModel extends ViewModel {
    private List<User> users;
    private final UserRepository repository;
    private final Executor executor;

    public LoggedViewModel(@NonNull UserRepository repository) {
        this.repository = repository;
        this.executor = UniversityApp.getAppExecutor();
    }

    public List<User> getUsers() {
        return users;
    }

    public void fetchUsers() {
        executor.execute(() -> {
            users = repository.getAll();
        });
    }
}
