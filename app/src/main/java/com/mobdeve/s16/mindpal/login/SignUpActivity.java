package com.mobdeve.s16.mindpal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobdeve.s16.mindpal.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        // Initialize the EditTexts and Button
        editTextUsername = findViewById(R.id.editTextUsername); // Replace with your actual ID
        editTextPassword = findViewById(R.id.editTextPassword); // Replace with your actual ID
        signUpButton = findViewById(R.id.CreateAccount_Button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addUser(username, password);

        // Notify user
        Toast.makeText(SignUpActivity.this, "Account created, Please login with your credentials", Toast.LENGTH_SHORT).show();

        // Redirect to login activity
        openLoginActivity();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
