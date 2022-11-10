package com.example.universityApp.repositories;

import com.example.universityApp.dto.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User insert(User user);
    void update(User user);
    void delete(User user);
}
