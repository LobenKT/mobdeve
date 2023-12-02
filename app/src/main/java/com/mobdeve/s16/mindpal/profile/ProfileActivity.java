package com.mobdeve.s16.mindpal.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.home.HomeActivity;
import com.mobdeve.s16.mindpal.home.MoodDialog;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

import java.util.ArrayList;

public class ProfileActivity extends HomeActivity implements NameDialog.NameDialogListener {

    TextView usernameText, dailyMood;
    ImageView profileImage;
    ArrayList<goals_model> goalModels = new ArrayList<>();
    Button addGoal, moodHistory;
    RecyclerView goal_recycler;
    goal_RecyclerViewAdaptor goalAdaptor;
    NameDialog nameDialog;
    ConfirmDialog confirmDialog;

    GoalDialog goalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // this should be declared first in ProfileActivity
        setContentView(R.layout.activity_profile);

        setupBottomNavigation(); // Setup NavBar

        usernameText = (TextView) findViewById(R.id.username_text);
        dailyMood = (TextView) findViewById(R.id.daily_Mood);
        System.out.println("usernameText: " + usernameText);
        //String username = getIntent().getStringExtra("KeyName");

        goal_recycler = (RecyclerView) findViewById(R.id.goals_recycler_view);
        goal_recycler.setNestedScrollingEnabled(false);

        profileImage = (ImageView) findViewById(R.id.profile_image);

        addGoal = (Button) findViewById(R.id.add_Goal_btn);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        String username = sharedPreferences.getString("Username", "User");
        String mood = sharedPreferences.getString("DailyMood", "Did Not Check in Yet");
        String ImageUri = dbHelper.getImage(username);
        Log.d("TAG", "Retrieved string: " + ImageUri);
        usernameText.setText(username);
        dailyMood.setText("Today's Mood: " + mood);
        Uri image = Uri.parse(ImageUri);
        profileImage.setImageURI(image);

        moodHistory = (Button) findViewById(R.id.mood_history_Button);
        moodHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHistory();
            }
        });

        goalModels = dbHelper.getGoals(username);
        goalAdaptor = new goal_RecyclerViewAdaptor(ProfileActivity.this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGoal(username);

            }
        });


        ImageView ivSettings = findViewById(R.id.iv_settings);
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ProfileActivity.this, v);
                popup.getMenuInflater().inflate(R.menu.settings_menu_profile, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.change_profile) {
                            // Handle change profile action

                            pickImage();
                            return true;
                        } else if (item.getItemId() == R.id.change_username) {
                            // Handle change username action
                            changeName();
                            return true;
                        } else if (item.getItemId() == R.id.logout) {
                            // Handle logout action
                            confirmation();
                            return true;
                        } else if (item.getItemId() == R.id.delete) {
                            // Handle delete action
                            confirmation();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popup.show();
            }

        });
    }

    private void changeName(){

        nameDialog = new NameDialog();
        nameDialog.show(getSupportFragmentManager(), "Enter new name");
    }

    private void addGoal(String username){
        goalDialog = new GoalDialog();
        goalDialog.show(getSupportFragmentManager(), "Enter Goal");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        goalModels = dbHelper.getGoals(username);
        goalAdaptor = new goal_RecyclerViewAdaptor(ProfileActivity.this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
    }

    private void confirmation(){
        confirmDialog = new ConfirmDialog();
        confirmDialog.show(getSupportFragmentManager(), "Confirmation");
    }

    @Override
    public void applyTexts(String NewName) {

        /*goalModels.add(new goals_model(GoalTitle, "Ongoing"));
        goalAdaptor = new goal_RecyclerViewAdaptor(this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(this)); */
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        String username = sharedPreferences.getString("Username", "User");
        dbHelper.updateUserName(username, NewName);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", NewName);
        editor.apply();
        usernameText.setText(NewName);
    }


    private void pickImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
            String strURI = selectedImage.toString();
            String username = sharedPreferences.getString("Username", "User");
            DatabaseHelper dbHelper = new DatabaseHelper(ProfileActivity.this);
            dbHelper.updateImage(strURI, username);
            Log.d("TAG", "Retrieved string: " + strURI);
            profileImage.setImageURI(selectedImage);
        }
    }

    private void viewHistory(){
        Intent intent = new Intent(ProfileActivity.this, View_MoodHistory.class);
        startActivity(intent);
    }

}