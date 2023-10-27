package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.mobdeve.s16.mindpal.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView WelcomeText;
    ArrayList<featured_model> featuredModels = new ArrayList<>();
    RecyclerView feature_recycler;
    featured_RecyclerAdaptor featureAdaptor;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        feature_recycler = findViewById(R.id.feature_recycler);

        WelcomeText = (TextView) findViewById(R.id.Welcome_Message);
        String username = getIntent().getStringExtra("KeyUsername");
        WelcomeText.setText("Welcome, " + username);

        featuredModels.add(new featured_model("Article Title" , "This is The Best Article" , R.drawable.sample_thumbnail));
        featuredModels.add(new featured_model("Article Title 2" , "This is The 2nd Best Article" , R.drawable.sample_thumbnail));
        featuredModels.add(new featured_model("Article Title 3" , "This is The 3rd Best Article" , R.drawable.sample_thumbnail));

        featureAdaptor = new featured_RecyclerAdaptor(this, featuredModels);
        feature_recycler.setAdapter(featureAdaptor);
        feature_recycler.setLayoutManager(new LinearLayoutManager(this));

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