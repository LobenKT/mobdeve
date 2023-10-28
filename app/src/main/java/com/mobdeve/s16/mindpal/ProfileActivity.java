package com.mobdeve.s16.mindpal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s16.mindpal.home.HomeActivity;

import java.util.ArrayList;

public class ProfileActivity extends HomeActivity implements GoalDialog.GoalDialogListener {

    TextView usernameText;
    ImageView profileImage;
    ArrayList<goals_model> goalModels = new ArrayList<>();
    Button addGoal;
    RecyclerView goal_recycler;
    goal_RecyclerViewAdaptor goalAdaptor;
    GoalDialog goalDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // this should be declared first in ProfileActivity
        setContentView(R.layout.activity_profile);

        setupBottomNavigation(); // Setup NavBar

        usernameText = (TextView) findViewById(R.id.username_text);
        System.out.println("usernameText: " + usernameText);
        String username = getIntent().getStringExtra("KeyName");
        goal_recycler = (RecyclerView) findViewById(R.id.goals_recycler_view);
        profileImage = (ImageView) findViewById(R.id.profile_image);
        addGoal = (Button) findViewById(R.id.add_Goal_btn);

        usernameText.setText(username);

        goalModels.add(new goals_model("Test Goal", "Ongoing"));
        goalModels.add(new goals_model("Test Goal2", "Completed"));
        goalModels.add(new goals_model("Test Goal3", "Ongoing"));

        goalAdaptor = new goal_RecyclerViewAdaptor(this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(this));

        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGoal("New Goal" , 100);
            }
        });
    }

    private void AddGoal(String goalTitle, int GoalValue){

        goalDialog = new GoalDialog();
        goalDialog.show(getSupportFragmentManager(), "Enter Goal");
    }

    @Override
    public void applyTexts(String GoalTitle) {

        goalModels.add(new goals_model(GoalTitle, "Ongoing"));
        goalAdaptor = new goal_RecyclerViewAdaptor(this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}