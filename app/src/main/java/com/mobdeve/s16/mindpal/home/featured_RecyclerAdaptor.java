package com.mobdeve.s16.mindpal.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.R;

import java.util.ArrayList;

public class featured_RecyclerAdaptor extends RecyclerView.Adapter<featured_RecyclerAdaptor.MyFeatureHolder> {
    Context context;
    ArrayList<featured_model> featuredModels;

    /*
    RANDOMCOM ABCDE
     */
    public featured_RecyclerAdaptor(Context context, ArrayList<featured_model> featuredModels) {
        this.context = context;
        this.featuredModels = featuredModels;
    }
    @NonNull
    @Override
    public featured_RecyclerAdaptor.MyFeatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.featured_rows, parent, false);
        return new featured_RecyclerAdaptor.MyFeatureHolder(view);
    }

    /*
    connects data to each xml card for article.
     */
    @Override
    public void onBindViewHolder(@NonNull featured_RecyclerAdaptor.MyFeatureHolder holder, int position) {
        holder.FeatureTitle.setText(featuredModels.get(position).getFeatured_Title());
        holder.FeatureDescription.setText(featuredModels.get(position).getFeatured_Description());
        holder.FeatureThumbnail.setImageResource(featuredModels.get(position).getFeatured_thumbnail());
    }

    /*
    * Function to be used to display the cards.
     */
    @Override
    public int getItemCount() {
        return featuredModels.size();
    }

    public static class MyFeatureHolder extends RecyclerView.ViewHolder{

        TextView FeatureTitle, FeatureDescription;
        ImageView FeatureThumbnail;

        public MyFeatureHolder(@NonNull View itemView) {
            super(itemView);

            FeatureTitle = itemView.findViewById(R.id.feature_title);
            FeatureDescription = itemView.findViewById(R.id.feature_description);
            FeatureThumbnail = itemView.findViewById(R.id.feature_thumbnail);
        }
    }
}
