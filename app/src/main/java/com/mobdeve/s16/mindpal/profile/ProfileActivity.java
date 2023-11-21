package com.mobdeve.s16.mindpal.profile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdeve.s16.mindpal.R;
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
    ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // this should be declared first in ProfileActivity
        setContentView(R.layout.activity_profile);

        setupBottomNavigation(); // Setup NavBar

        usernameText = (TextView) findViewById(R.id.username_text);
        System.out.println("usernameText: " + usernameText);
        //String username = getIntent().getStringExtra("KeyName");

        goal_recycler = (RecyclerView) findViewById(R.id.goals_recycler_view);
        goal_recycler.setNestedScrollingEnabled(false);

        profileImage = (ImageView) findViewById(R.id.profile_image);

        addGoal = (Button) findViewById(R.id.add_Goal_btn);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "User");
        //String ImageUri = sharedPreferences.getString("ProfileImage", "Image");
        usernameText.setText(username);
        //profileImage.setImageURI(Uri.parse(ImageUri));

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

    private void AddGoal(String goalTitle, int GoalValue){

        goalDialog = new GoalDialog();
        goalDialog.show(getSupportFragmentManager(), "Enter Goal");
    }

    private void confirmation(){
        confirmDialog = new ConfirmDialog();
        confirmDialog.show(getSupportFragmentManager(), "Confirmation");
    }

    @Override
    public void applyTexts(String GoalTitle) {

        goalModels.add(new goals_model(GoalTitle, "Ongoing"));
        goalAdaptor = new goal_RecyclerViewAdaptor(this , goalModels);
        goal_recycler.setAdapter(goalAdaptor);
        goal_recycler.setLayoutManager(new LinearLayoutManager(this));
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
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String strURI = selectedImage.toString();
            editor.putString("ProfileImage" , strURI);
            editor.apply();
            profileImage.setImageURI(selectedImage);
        }
    }

}