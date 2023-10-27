package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView navView;
    TextView usernameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        usernameText = (TextView) findViewById(R.id.username_text);
        String username = getIntent().getStringExtra("KeyName");
        usernameText.setText(username);

        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Home_btn){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else if (item.getItemId() == R.id.Settings_btn){

            }

            return true;
        });
    }
}