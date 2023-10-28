package com.mobdeve.s16.mindpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> alarmList;

    public AlarmAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        holder.timeTextView.setText(alarm.getTime());
        holder.repeatTextView.setText("Repeat: " + alarm.getRepeat());
        holder.labelTextView.setText("Label: " + alarm.getLabel());

        // Handle the delete button click
        holder.deleteButton.setOnClickListener(v -> {
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
