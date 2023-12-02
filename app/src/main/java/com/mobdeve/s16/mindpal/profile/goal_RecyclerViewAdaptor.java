package com.mobdeve.s16.mindpal.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

import java.util.ArrayList;

public class goal_RecyclerViewAdaptor extends RecyclerView.Adapter<goal_RecyclerViewAdaptor.MyViewHolder> {

    Context context;
    ArrayList<goals_model> goalModels;
    public goal_RecyclerViewAdaptor(Context context, ArrayList<goals_model> goalModels){
        this.context = context;
        this.goalModels = goalModels;
    }

    @NonNull
    @Override
    public goal_RecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.goals_row_profile, parent, false);
        return new goal_RecyclerViewAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull goal_RecyclerViewAdaptor.MyViewHolder holder, int position) {

        holder.GoalTitle.setText(goalModels.get(position).getGoalTitle());
        holder.GoalProgress.setText(goalModels.get(position).getCurrentProgress());
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        int id = goalModels.get(position).getId();
        holder.statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.goal_status_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.Ongoing_btn){
                            dbhelper.updatedGoalStatus(id, "Ongoing");
                            holder.GoalProgress.setText("Ongoing");
                            return true;
                        } else if (item.getItemId() == R.id.Hold_btn){
                            dbhelper.updatedGoalStatus(id, "On Hold");
                            holder.GoalProgress.setText("On Hold");
                            return true;
                        }else if (item.getItemId() == R.id.Complete_btn){
                            dbhelper.updatedGoalStatus(id, "Complete");
                            holder.GoalProgress.setText("Complete");
                            return true;
                        }else {
                            return false;
                        }
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return goalModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView GoalTitle;
        TextView GoalProgress;
        Button statusBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            GoalTitle = itemView.findViewById(R.id.Goal_Title);
            GoalProgress = itemView.findViewById(R.id.progress_text);
            statusBtn = itemView.findViewById(R.id.update_status);
        }
    }
}
