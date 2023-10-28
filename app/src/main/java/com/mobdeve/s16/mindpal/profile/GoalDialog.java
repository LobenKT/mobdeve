package com.mobdeve.s16.mindpal.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.mobdeve.s16.mindpal.R;

public class GoalDialog extends AppCompatDialogFragment {

    private EditText inputGoal;
    private GoalDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.goal_popup, null);

        builder.setView(view)
                .setTitle("Create Goal")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String GoalTitle = inputGoal.getText().toString();
                        listener.applyTexts(GoalTitle);
                    }
                });
        inputGoal = view.findViewById(R.id.input_goal_title);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (GoalDialogListener) context;
    }

    public interface GoalDialogListener {
        void applyTexts(String GoalTitle);
    }
}
