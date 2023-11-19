package com.mobdeve.s16.mindpal.notification;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NotificationsActivity extends NavigationActivity implements AddAlarmDialogFragment.OnInputListener {

    private RecyclerView recyclerView;
    private FloatingActionButton addAlarmButton;
    private List<Alarm> alarms = new ArrayList<>();
    private AlarmAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setupBottomNavigation();

        recyclerView = findViewById(R.id.alarms_recyclerView);
        addAlarmButton = findViewById(R.id.add_alarm_btn);

        // Sample data
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("09:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("10:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("09:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("10:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("09:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("10:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("09:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("10:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("09:00 AM", "Daily", "Morning Alarm"));
        alarms.add(new Alarm("10:00 AM", "Daily", "Morning Alarm"));

        adapter = new AlarmAdapter(alarms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addAlarmButton.setOnClickListener(v -> {
            AddAlarmDialogFragment addAlarmDialog = new AddAlarmDialogFragment();
            addAlarmDialog.show(getSupportFragmentManager(), "Add Alarm Dialog");
        });

    }

    public void startAlarm(Calendar c, String Label) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        //intent.putExtra("Label", Label);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    @Override
    public void sendInput(String Label, int hours, int minutes) {
        Calendar c = Calendar.getInstance();

        //Set Up For Arraylist / add to alarms list
        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        String Time = FormatTimeString(hours, minutes);
        alarms.add(new Alarm(Time, "Daily", Label));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the Alarm
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, 0);

        startAlarm(c, Label);
    }

    public String FormatTimeString (int hours, int minutes){
        String Time;
        if (hours > 12){
            hours = hours - 12;
            Time = hours + ":" + minutes + " PM";
        }
        else {
            Time = hours + ":" + minutes + " AM";
        }
        return Time;
    }

}
