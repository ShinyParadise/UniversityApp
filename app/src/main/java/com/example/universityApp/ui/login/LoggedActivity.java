package com.example.universityApp;

import android.os.Bundle;

import com.example.universityApp.databinding.ActivityLoggedBinding;
import com.example.universityApp.db.AppDatabase;
import com.example.universityApp.db.dao.UserDAO;
import com.example.universityApp.repositories.UserRepositoryImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class LoggedActivity extends AppCompatActivity {
    private LoggedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoggedBinding binding = ActivityLoggedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        buildNavController(navView);

        UserDAO userDAO = AppDatabase.getDatabase(getApplicationContext()).userDAO();

        viewModel = new LoggedViewModel(new UserRepositoryImpl(userDAO));
        viewModel.getUsers();
    }

    private void buildNavController(BottomNavigationView navView) {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_book_list,
                R.id.navigation_selected_book,
                R.id.navigation_fav_books).build();
        NavController navController = Navigation.findNavController(
                this,
                R.id.nav_host_fragment_activity_logged);
        NavigationUI.setupActionBarWithNavController(
                this,
                navController,
                appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
