package com.mobdeve.s16.mindpal.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.R;
import com.squareup.picasso.Picasso;

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
        View view = inflater.inflate(R.layout.featured_rows_home, parent, false);
        return new featured_RecyclerAdaptor.MyFeatureHolder(view);
    }

    /*
    connects data to each xml card for article.
     */
    @Override
    public void onBindViewHolder(@NonNull MyFeatureHolder holder, int position) {
        featured_model model = featuredModels.get(position);
        holder.FeatureTitle.setText(model.getFeatured_Title());
        holder.FeatureDescription.setText(model.getFeatured_Description());

        // Truncate long text
        truncateText(holder.FeatureTitle, 25); // Truncate title if it's too long
        truncateText(holder.FeatureDescription, 50); // Truncate description if it's too long

        Picasso.get().load(model.getFeatured_thumbnail()).into(holder.FeatureThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the article URL
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(model.getArticleUrl()));
                context.startActivity(i);
            }
        });
    }

    private void truncateText(TextView textView, int maxLength) {
        if (textView.getText().length() > maxLength) {
            String truncatedText = textView.getText().toString().substring(0, maxLength) + "...";
            textView.setText(truncatedText);
        }
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

            FeatureTitle = itemView.findViewById(R.id.meditate_title);
            FeatureDescription = itemView.findViewById(R.id.meditate_description);
            FeatureThumbnail = itemView.findViewById(R.id.meditate_thumbnail);
        }
    }
}
