package com.mobdeve.s16.mindpal.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.R;

import java.util.ArrayList;

public class Mood_RecyclerAdaptor extends RecyclerView.Adapter<Mood_RecyclerAdaptor.MyViewHolder> {

    Context context;
    ArrayList<Mood_Model> moodModels;

    public Mood_RecyclerAdaptor(Context context, ArrayList<Mood_Model> moodModels) {
        this.context = context;
        this.moodModels = moodModels;
    }

    @NonNull
    @Override
    public Mood_RecyclerAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mood_rows, parent, false);
        return new Mood_RecyclerAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mood_RecyclerAdaptor.MyViewHolder holder, int position) {
        holder.Mood_Txt.setText(moodModels.get(position).getContent());
        holder.Date_Txt.setText(moodModels.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return moodModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Mood_Txt;
        TextView Date_Txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Mood_Txt = itemView.findViewById(R.id.Mood_text);
            Date_Txt = itemView.findViewById(R.id.Date_Text);
        }
    }
}
