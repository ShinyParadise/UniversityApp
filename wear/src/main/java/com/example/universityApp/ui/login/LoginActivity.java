package com.example.universityApp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universityApp.R;
import com.example.universityApp.ui.bookList.MainActivity;

public class LoginActivity extends Activity {
    private String[] logins;
    private String[] passwords;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Resources res = getResources();
        logins = res.getStringArray(R.array.logins);
        passwords = res.getStringArray(R.array.passwords);

        etEmail = findViewById(R.id.login);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            int pos = searchLogin(email);
            if (pos != -1 && passwords[pos].equals(password)) {
                Intent launch = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(launch);
            } else {
                Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int searchLogin(String login) {
        for (int i = 0; i<logins.length; i++) {
            if (login.equals(logins[i])) {
                return i;
            }
        }
        return -1;
    }
}
