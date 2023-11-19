package com.mobdeve.s16.mindpal.meditate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MeditateActivity extends NavigationActivity {

    ArrayList<meditation_courses> meditationCourses = new ArrayList<>();
    ArrayList<meditation_courses> filteredCourses = new ArrayList<>();
    meditation_courses CourseHolder;
    RecyclerView meditation_recycler;
    meditation_Adaptor meditationAdaptor;
    Button sleep_btn , focus_btn, relax_btn, happy_btn, reset_btn;
    String API = "AIzaSyDT3u9rFL-G4cQx_HHnzd_qDPc-2CA7XOo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        setupBottomNavigation();

        sleep_btn = findViewById(R.id.sleep_meditate);
        focus_btn = findViewById(R.id.focus_meditate);
        relax_btn = findViewById(R.id.relax_meditate);
        happy_btn = findViewById(R.id.happiness_meditate);
        reset_btn = findViewById(R.id.reset_filter);
        meditation_recycler = findViewById(R.id.meditate_recycler);

        // Test Values
/*
        meditationCourses.add(new meditation_courses("Sleep", "Sleep Meditation", "You Feel Sleep now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Sleep", "Sleep Meditation2", "You Feel Sleep now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Focus", "Focus Meditation", "You Feel Focus now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Focus", "Focus Meditation", "You Feel Focus now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Relax", "Relax Meditation", "You Feel Relax now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Relax", "Relax Meditation", "You Feel Relax now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Happiness", "Happy Meditation", "You Feel Happy now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Happiness", "Happy Meditation", "You Feel Happy now fr", R.drawable.sample_thumbnail));
*/
        meditationAdaptor = new meditation_Adaptor(this, meditationCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));

        sleep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SleepCourse(meditationCourses);
            }
        });

        focus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusCourse(meditationCourses);
            }
        });

        relax_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelaxCourse(meditationCourses);
            }
        });

        happy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HappyCourse(meditationCourses);
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetCourse(meditationCourses);
            }
        });

        new YoutubeVideo().execute();
    }

    class YoutubeVideo extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.meditate_recycler).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=5&key=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray item = jsonObj.getJSONArray("items");
                for (int i = 0; i < item.length() ; i++) {
                    JSONObject JSONObject1= item.getJSONObject(i);
                    JSONObject jsonSnippet = JSONObject1.getJSONObject("snippet");
                    String videoTitle = jsonSnippet.getString("title");
                    String videoDesc = jsonSnippet.getString("description");
                    String videoThumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                    meditationCourses.add(new meditation_courses("Sleep", videoTitle, videoDesc, videoThumbnail));
                    Log.d("TAG", "onPostExecute: videoTitle " + videoTitle);
                }
                meditation_recycler.setAdapter(meditationAdaptor);
                meditation_recycler.setLayoutManager(new LinearLayoutManager(MeditateActivity.this));
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.meditate_recycler).setVisibility(View.VISIBLE);
                //Toast.makeText(MeditateActivity.this, "Video Title: " + videoTitle, Toast.LENGTH_LONG).show();
            }catch (JSONException e){
                Toast.makeText(MeditateActivity.this, "Sira Pre", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void SleepCourse(ArrayList<meditation_courses> meditationCourses){
        filteredCourses.clear();
        for (int i = 0 ; i < meditationCourses.size(); i++){
            if (meditationCourses.get(i).Category.equalsIgnoreCase("Sleep")){
                CourseHolder = meditationCourses.get(i);
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail()));
            }
        }
        meditationAdaptor = new meditation_Adaptor(this, filteredCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void FocusCourse(ArrayList<meditation_courses> meditationCourses){
        filteredCourses.clear();
        for (int i = 0 ; i < meditationCourses.size(); i++){
            if (meditationCourses.get(i).Category.equalsIgnoreCase("Focus")){
                CourseHolder = meditationCourses.get(i);
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail()));
            }
        }
        meditationAdaptor = new meditation_Adaptor(this, filteredCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void RelaxCourse(ArrayList<meditation_courses> meditationCourses){
        filteredCourses.clear();
        for (int i = 0 ; i < meditationCourses.size(); i++){
            if (meditationCourses.get(i).Category.equalsIgnoreCase("Relax")){
                CourseHolder = meditationCourses.get(i);
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail()));
            }
        }
        meditationAdaptor = new meditation_Adaptor(this, filteredCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void HappyCourse(ArrayList<meditation_courses> meditationCourses){
        filteredCourses.clear();
        for (int i = 0 ; i < meditationCourses.size(); i++){
            if (meditationCourses.get(i).Category.equalsIgnoreCase("Happiness")){
                CourseHolder = meditationCourses.get(i);
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail()));
            }
        }
        meditationAdaptor = new meditation_Adaptor(this, filteredCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void ResetCourse(ArrayList<meditation_courses> meditationCourses){
        filteredCourses.clear();
        meditationAdaptor = new meditation_Adaptor(this, meditationCourses);
        meditation_recycler.setAdapter(meditationAdaptor);
        meditation_recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}