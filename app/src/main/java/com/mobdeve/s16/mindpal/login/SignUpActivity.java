package com.mobdeve.s16.mindpal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        Uri uri = getUriToDrawable(this, R.drawable.profile_asset);
        String image = uri.toString();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser(image);
            }
        });
    }

    private void registerNewUser(String image) {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addUser(username, password, image);

        // Notify user
        Toast.makeText(SignUpActivity.this, "Account created, Please login with your credentials", Toast.LENGTH_SHORT).show();

        // Redirect to login activity
        openLoginActivity();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public static Uri getUriToDrawable(Context context, int drawableId) {
        Uri imageUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + drawableId);
        return imageUri;
    }
}
