package com.example.universityApp.ui.mainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universityApp.R;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.UserDAO;
import com.example.universityApp.dto.User;
import com.example.universityApp.repositories.userRepo.UserRepositoryImpl;
import com.example.universityApp.ui.loggedScreen.LoggedActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private SharedPreferences prefs;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private String[] logins;
    private String[] passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("com.example.universityApp", MODE_PRIVATE);

        initiateViews();
        initResources();
        initViewModel();

        if (prefs.getBoolean("isFirstRun", true)) {
            insertUsers();
            prefs.edit().putBoolean("isFirstRun", false).apply();
        }

        btnLogin.setOnClickListener(this::onLoginClick);
    }

    private void initViewModel() {
        UserDAO userDAO = AppDatabase.getDatabase(getApplicationContext()).userDAO();
        viewModel = new LoginViewModel(new UserRepositoryImpl(userDAO));
    }

    private void initResources() {
        Resources res = getResources();
        logins = res.getStringArray(R.array.logins);
        passwords = res.getStringArray(R.array.passwords);
    }

    private void insertUsers() {
        for (int i = 0; i < logins.length; i++) {
            User newUser = new User(logins[i], passwords[i]);
            viewModel.insertUser(newUser);
        }
    }

    private void initiateViews() {
        etEmail = findViewById(R.id.login);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void onLoginClick(View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        User userToValidate = new User(email, password);

        boolean isUserValid = viewModel.validateUser(userToValidate);
        long userId = -1;

        if (isUserValid) {
            List<User> users = viewModel.getUsers();
            for (User user : users) {
                if (user.getLogin().equals(userToValidate.getLogin())) {
                    userId = user.getId();
                }
            }
            prefs.edit().putLong("userId", userId).apply();

            Intent launchLoggedScreen = new Intent(getApplicationContext(), LoggedActivity.class);
            startActivity(launchLoggedScreen);
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
