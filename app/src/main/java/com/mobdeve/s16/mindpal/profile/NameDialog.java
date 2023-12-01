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

public class NameDialog extends AppCompatDialogFragment {

    private EditText inputName;
    private NameDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_name_profile, null);

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
                        String GoalTitle = inputName.getText().toString();
                        listener.applyTexts(GoalTitle);

                        new AlertDialog.Builder(getContext())
                                .setTitle("Congratulations!")
                                .setMessage("Username Changed Successfully!")
                                .setPositiveButton("OK", null)
                                .show();

                        // Display the toast message
                        //Toast.makeText(getContext(), "Goal set successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
        inputName = view.findViewById(R.id.input_new_name);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (NameDialogListener) context;
    }

    public interface NameDialogListener {
        void applyTexts(String NewName);
    }

}
