package com.mobdeve.s16.mindpal.meditate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;


import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.home.featured_RecyclerAdaptor;

import java.util.ArrayList;

public class meditation_Adaptor extends RecyclerView.Adapter<meditation_Adaptor.MyMeditationHolder>{

    Context context;
    ArrayList<meditation_courses> meditation_courses ;

    public meditation_Adaptor(Context context, ArrayList<com.mobdeve.s16.mindpal.meditate.meditation_courses> meditation_courses) {
        this.context = context;
        this.meditation_courses = meditation_courses;
    }

    @NonNull
    @Override
    public meditation_Adaptor.MyMeditationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meditation_rows_meditate, parent, false);
        return new meditation_Adaptor.MyMeditationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull meditation_Adaptor.MyMeditationHolder holder, int position) {
        holder.meditationTitle.setText(meditation_courses.get(position).getTitle());
        holder.meditationDescription.setText(meditation_courses.get(position).getDescription());
        String URL = meditation_courses.get(position).getThumbnail();
        Picasso.get().load(URL).into(holder.meditationThumbnail);
        //holder.meditationThumbnail.setImageResource(meditation_courses.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return meditation_courses.size();
    }

    public static class MyMeditationHolder extends RecyclerView.ViewHolder{

        TextView meditationTitle , meditationDescription;
        ImageView meditationThumbnail;
        public MyMeditationHolder(@NonNull View itemView) {
            super(itemView);

            meditationTitle = itemView.findViewById(R.id.meditate_title);
            meditationDescription = itemView.findViewById(R.id.meditate_description);
            meditationThumbnail = itemView.findViewById(R.id.meditate_thumbnail);
        }
    }


}
