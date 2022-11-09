package com.example.universityApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;

    private String email, password;

    private String[] logins;
    private String[] passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        logins = res.getStringArray(R.array.logins);
        passwords = res.getStringArray(R.array.passwords);

        etEmail = findViewById(R.id.login);
        etPassword = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            int loginFound = findLogin(email);
            if (loginFound != -1) {
                if (isPasswordCorrect(password, loginFound)) {
                    Intent launchLoggedScreen = new Intent(getApplicationContext(), LoggedActivity.class);
                    startActivity(launchLoggedScreen);
                } else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int findLogin(String loginToSearch) {
        for (int i = 0; i< logins.length; i++) {
            if (logins[i].equals(loginToSearch))
                return i;
        }
        return -1;
    }

    private boolean isPasswordCorrect(String pass, int resID) {
        return passwords[resID].equals(pass);
    }
}
