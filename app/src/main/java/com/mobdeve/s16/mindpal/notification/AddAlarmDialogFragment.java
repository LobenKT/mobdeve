package com.mobdeve.s16.mindpal.notification;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.mobdeve.s16.mindpal.R;

import java.util.Calendar;

public class AddAlarmDialogFragment extends DialogFragment {

    private TimePicker timePicker;
    private Spinner repeatSpinner;
    private EditText labelEditText;
    private LinearLayout daysLayout;
    private String repeat;
    private Button addAlarmButton;
    private CheckBox[] dayCheckboxes;

    private final String[] dayLabels = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private final int[] checkboxIds = {R.id.sunCheckbox, R.id.monCheckbox, R.id.tueCheckbox, R.id.wedCheckbox, R.id.thuCheckbox, R.id.friCheckbox, R.id.satCheckbox};
    public interface OnInputListener {
        void sendInput(String Label, String repeat,  int hours, int minutes);
    }
    public OnInputListener inputListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_notification, null);

        timePicker = view.findViewById(R.id.timePicker);
        repeatSpinner = view.findViewById(R.id.repeatSpinner);
        labelEditText = view.findViewById(R.id.labelEditText);
        daysLayout = view.findViewById(R.id.daysLayout);
        addAlarmButton = view.findViewById(R.id.addAlarmButton);
        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        dayCheckboxes = new CheckBox[7];
        for (int i = 0; i < 7; i++) {
            dayCheckboxes[i] = view.findViewById(checkboxIds[i]);
        }

        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if ("Custom".equals(parent.getItemAtPosition(position))) {
                    daysLayout.setVisibility(View.VISIBLE);
                } else {
                    repeat = parent.getItemAtPosition(position).toString();
                    daysLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addAlarmButton.setOnClickListener(v -> {
            // Logic to capture the alarm details, including selected days
            String selectedDays = getSelectedDays();
            // TODO: Save the alarm with the given details
            String Label = labelEditText.getText().toString();
            int hours = timePicker.getHour();
            int minutes = timePicker.getMinute();
            inputListener.sendInput(Label, repeat,hours, minutes);
            Toast.makeText(getActivity(), "Alarm Added", Toast.LENGTH_LONG).show();
            dismiss();
        });

        builder.setView(view)
                .setTitle("Add Alarm");

        return builder.create();
    }

    private String getSelectedDays() {
        StringBuilder days = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            if (dayCheckboxes[i].isChecked()) {
                days.append(dayLabels[i]).append(" ");
            }
        }
        return days.toString().trim();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            inputListener = (OnInputListener) getActivity();

        }catch (ClassCastException e){
            Log.e("DialogTag", "onAttach: ClassCastException");
        }
    }
}

