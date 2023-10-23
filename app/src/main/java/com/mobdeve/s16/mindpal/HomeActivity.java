package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView WelcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WelcomeText = (TextView) findViewById(R.id.Welcome_Text);
        String username = getIntent().getStringExtra("KeyUsername");
        WelcomeText.setText("Welcome, " + username);
    }
}