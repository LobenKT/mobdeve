package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addAlarmButton;
    private List<Alarm> alarms = new ArrayList<>();
    private AlarmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = findViewById(R.id.alarms_recyclerView);
        addAlarmButton = findViewById(R.id.add_alarm_btn);

        // Sample data
        alarms.add(new Alarm("08:00 AM", "Daily", "Morning Alarm"));
        adapter = new AlarmAdapter(alarms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addAlarmButton.setOnClickListener(v -> {
            AddAlarmDialogFragment addAlarmDialog = new AddAlarmDialogFragment();
            addAlarmDialog.show(getSupportFragmentManager(), "Add Alarm Dialog");
        });

    }
}