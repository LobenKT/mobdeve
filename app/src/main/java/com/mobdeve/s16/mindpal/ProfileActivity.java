package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements GoalDialog.GoalDialogListener {

    BottomNavigationView navView;
    TextView usernameText;
    ImageView profileImage;
    ArrayList<goals_model> goalModels = new ArrayList<>();
    Button addGoal;
    RecyclerView goal_recycler;
    goal_RecyclerViewAdaptor goalAdaptor;
    GoalDialog goalDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        usernameText = (TextView) findViewById(R.id.username_text);
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