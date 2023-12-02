package com.mobdeve.s16.mindpal.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

public class GoalDialog extends AppCompatDialogFragment {
    private EditText inputGoal;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_goal_profile, null);
        inputGoal = (EditText) view.findViewById(R.id.input_goal);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "User");
        int userID = sharedPreferences.getInt("ID", 0);
        builder.setView(view)
                .setTitle("Change Username")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
                        String goal = inputGoal.getText().toString();
                        dbhelper.addGoal(userID, goal);

                        new AlertDialog.Builder(getContext())
                                .setTitle("Congratulations!")
                                .setMessage("Goal Added Successfully!")
                                .setPositiveButton("OK", null)
                                .show();

                        // Display the toast message
                        //Toast.makeText(getContext(), "Goal set successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
