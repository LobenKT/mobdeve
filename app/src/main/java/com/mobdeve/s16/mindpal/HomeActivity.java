package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.mobdeve.s16.mindpal.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {

    TextView WelcomeText;

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        WelcomeText = (TextView) findViewById(R.id.Welcome_Message);
        String username = getIntent().getStringExtra("KeyUsername");
        WelcomeText.setText("Welcome, " + username);

        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Profile_btn){
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("KeyName", username);
                startActivity(intent);
            }
            else if (item.getItemId() == R.id.Settings_btn){

            }

            return true;
        });
    }
}