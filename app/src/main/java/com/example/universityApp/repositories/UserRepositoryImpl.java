package com.example.universityApp.repositories;

import androidx.annotation.NonNull;

import com.example.universityApp.db.dao.UserDAO;
import com.example.universityApp.dto.User;

import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDAO;

    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAll() {
        List<com.example.universityApp.db.models.User> dbUsers = userDAO.getAll();
        return toUsers(dbUsers);
    }

    @NonNull
    private List<User> toUsers(@NonNull List<com.example.universityApp.db.models.User> dbUsers) {
        List<User> users = new LinkedList<>();
        dbUsers.forEach(user -> users.add(new User(user.login, user.password)));

        return users;
    }

    @Override
    public User insert(User user) {
        com.example.universityApp.db.models.User dbUser = toDbUser(user);

        long insertedId = userDAO.insert(dbUser);

        user.setId(insertedId);
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.update(toDbUser(user));
    }

    @Override
    public void delete(User user) {
        userDAO.delete(toDbUser(user));
    }

    @NonNull
    private com.example.universityApp.db.models.User toDbUser(@NonNull User user) {
        return new com.example.universityApp.db.models.User(user.getLogin(), user.getPassword());
    }
}
