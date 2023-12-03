package com.mobdeve.s16.mindpal.notification;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        int userID = sharedPreferences.getInt("ID", 0);
        // Sample data
        alarms = dbHelper.getAlarms(userID);

        adapter = new AlarmAdapter(this, alarms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addAlarmButton.setOnClickListener(v -> {
            AddAlarmDialogFragment addAlarmDialog = new AddAlarmDialogFragment();
            addAlarmDialog.show(getSupportFragmentManager(), "Add Alarm Dialog");
        });

    }

    public void startAlarm(Calendar c, int alarmID, String Label, String repeat) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("KeyLabel", Label);
        intent.putExtra("KeyID", alarmID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmID, intent, PendingIntent.FLAG_IMMUTABLE);

        if (repeat.equalsIgnoreCase("Daily")){
            long repeatInterval = AlarmManager.INTERVAL_DAY;
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), repeatInterval, pendingIntent);
        }else if (repeat.equalsIgnoreCase("Weekly")){
            long repeatInterval = AlarmManager.INTERVAL_DAY * 7;
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), repeatInterval, pendingIntent);
        }else if (repeat.equalsIgnoreCase("Once")) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    //Kulang pa to, cant target a specific alarm
    public void CancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void sendInput(String Label, String repeat, int hours, int minutes) {
        Calendar c = Calendar.getInstance();

        //Set Up For Arraylist / add to alarms list
        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        String Time = FormatTimeString(hours, minutes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add Alarm to DB
        DatabaseHelper dbhelper = new DatabaseHelper(NotificationsActivity.this);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        int userID = sharedPreferences.getInt("ID", 0);
        // Get Date
        Date Today = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = dateFormat.format(Today);

        dbhelper.addAlarm(userID, Time, hours, minutes, formattedDate, repeat, Label);
        // Set the Alarm
        int alarmID = dbhelper.getAlarmID(userID, hours, minutes, formattedDate, repeat, Label);
        //alarms.add(new Alarm(alarmID, Time, hours, minutes, formattedDate, "Daily", Label));
        alarms = dbhelper.getAlarms(userID);
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, 0);

        // Add new Alarm to the Recycler
        adapter = new AlarmAdapter(this, alarms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        startAlarm(c, alarmID, Label, repeat);
    }

    public String FormatTimeString (int hours, int minutes){
        String Time;
        String Minutes = String.valueOf(minutes);
        if (minutes < 10){
            Minutes = "0" + minutes;
        }
        if (hours > 12){
            hours = hours - 12;
            Time = hours + ":" + Minutes + " PM";
        }
        else {
            Time = hours + ":" + Minutes + " AM";
        }
        return Time;
    }

}
