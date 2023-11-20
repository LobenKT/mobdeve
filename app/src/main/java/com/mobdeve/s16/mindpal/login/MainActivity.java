package com.mobdeve.s16.mindpal.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    Button LogInButton;
    Button SignUpButton;
    EditText UserName;

    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        LogInButton = (Button)findViewById(R.id.login_button);
        SignUpButton = (Button)findViewById(R.id.signup_button);
        UserName = (EditText) findViewById(R.id.editTextUsername);
        Password = (EditText) findViewById(R.id.editTextPassword);

        // Inside MainActivity

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = UserName.getText().toString();
                String password = Password.getText().toString(); // Get password from EditText

                DatabaseHelper db = new DatabaseHelper(MainActivity.this);

                if (db.checkUser(username, password)) {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    openHomeActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }

    public void openHomeActivity(){
        String username = UserName.getText().toString();

        // Save username to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.apply();

        // Start HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

