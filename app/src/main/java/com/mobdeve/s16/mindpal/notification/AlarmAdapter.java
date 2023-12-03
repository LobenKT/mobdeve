package com.mobdeve.s16.mindpal.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    Context context;
    private List<Alarm> alarmList;

    public AlarmAdapter(Context context, List<Alarm> alarmList) {

        this.context = context;
        this.alarmList = alarmList;
    }


    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifications, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        int alarmID = alarm.getAlarm_id();
        holder.timeTextView.setText(alarm.getFormattedTime());
        holder.repeatTextView.setText("Repeat: " + alarm.getRepeat());
        holder.labelTextView.setText("Label: " + alarm.getLabel());



        // Handle the delete button click
        holder.deleteButton.setOnClickListener(v -> {
            //Cancel the Alarm
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.cancel(pendingIntent);
            // Remove the alarm from Database
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            dbHelper.deleteAlarm(alarmID);
            alarmList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, alarmList.size());

        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView, repeatTextView, labelTextView;
        Button deleteButton; // Reference for the delete button

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.alarm_time_textView);
            repeatTextView = itemView.findViewById(R.id.alarm_repeat_textView);
            labelTextView = itemView.findViewById(R.id.alarm_label_textView);
            deleteButton = itemView.findViewById(R.id.deleteAlarmButton); // Initialize the delete button
        }
    }
}
