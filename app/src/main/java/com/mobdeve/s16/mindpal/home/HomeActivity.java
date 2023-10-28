package com.mobdeve.s16.mindpal.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import com.mobdeve.s16.mindpal.BaseActivity;
import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.home.featured_RecyclerAdaptor;
import com.mobdeve.s16.mindpal.home.featured_model;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    TextView WelcomeText;
    ArrayList<featured_model> featuredModels = new ArrayList<>();
    RecyclerView feature_recycler;
    featured_RecyclerAdaptor featureAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // this should be declared first in HomeActivity


        setupBottomNavigation(); // Setup NavBar

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

    }
}