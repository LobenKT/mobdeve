package com.mobdeve.s16.mindpal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobdeve.s16.mindpal.home.HomeActivity;
import com.mobdeve.s16.mindpal.notification.NotificationsActivity;
import com.mobdeve.s16.mindpal.profile.ProfileActivity;

import android.content.Intent;

public abstract class BaseActivity extends AppCompatActivity {

    BottomNavigationView navView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupBottomNavigation() {
        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent = null;

            if (itemId == R.id.Home_btn && !this.getClass().equals(HomeActivity.class)) {
                intent = new Intent(this, HomeActivity.class);
            } else if (itemId == R.id.Profile_btn && !this.getClass().equals(ProfileActivity.class)) {
                intent = new Intent(this, ProfileActivity.class);
            } else if (itemId == R.id.Notification_btn && !this.getClass().equals(NotificationsActivity.class)) {
                intent = new Intent(this, NotificationsActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
            }

            return true;
        });
    }

}
