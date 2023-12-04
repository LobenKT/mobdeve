package com.mobdeve.s16.mindpal.home;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoodDialog  extends AppCompatDialogFragment {
    private EditText inputMood;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.checkin_mood_home, null);
        inputMood = view.findViewById(R.id.Input_Mood);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("ID", 0);
        builder.setView(view)
                .setTitle("Mood Check in")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Mood = inputMood.getText().toString();
                        Calendar calendar = Calendar.getInstance();
                        Date Today = calendar.getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        String formattedDate = dateFormat.format(Today);
                        // Place Mood on MoodTable
                        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                        dbHelper.addMood(userID, Mood, formattedDate);


                        // Display the toast message
                        //Toast.makeText(getContext(), "Goal set successfully!", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}
