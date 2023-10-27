package com.mobdeve.s16.mindpal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

public abstract class BaseActivity extends AppCompatActivity {

    BottomNavigationView navView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void setupBottomNavigation() {
        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent;
            if (itemId == R.id.Home_btn) {
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                System.out.println("item1:" + item);

            } else if (itemId == R.id.Profile_btn) {
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                System.out.println("item2:" + item);
            } else if (itemId == R.id.Settings_btn) {
                // Handle settings
            }
            return true;
        });
    }
}