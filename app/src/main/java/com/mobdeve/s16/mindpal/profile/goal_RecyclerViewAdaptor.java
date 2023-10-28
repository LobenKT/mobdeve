package com.mobdeve.s16.mindpal.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.R;

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
        View view = inflater.inflate(R.layout.profile_goals_row, parent, false);
        return new goal_RecyclerViewAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull goal_RecyclerViewAdaptor.MyViewHolder holder, int position) {

        holder.GoalTitle.setText(goalModels.get(position).getGoalTitle());
        holder.GoalProgress.setText(goalModels.get(position).getCurrentProgress());

    }

    @Override
    public int getItemCount() {
        return goalModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView GoalTitle;
        TextView GoalProgress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            GoalTitle = itemView.findViewById(R.id.Goal_Title);
            GoalProgress = itemView.findViewById(R.id.progress_text);
        }
    }
}
