package com.mobdeve.s16.mindpal.meditate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androdocs.httprequest.HttpRequest;
import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

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
    String API = "AIzaSyDsLJA942LpQgKjT18qsUyY5R1mc-qLOCU";

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

        new SleepVideo().execute();
        new FocusVideo().execute();
        new RelaxVideo().execute();
        new HappyVideo().execute();
    }

    class SleepVideo extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.meditate_recycler).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=sleep+meditation+exercises&maxResults=5&key=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray item = jsonObj.getJSONArray("items");
                for (int i = 0; i < item.length() ; i++) {
                    JSONObject JSONObject1= item.getJSONObject(i);
                    JSONObject jsonID = JSONObject1.getJSONObject("id");
                    if (jsonID.getString("kind").equalsIgnoreCase("youtube#video")) {
                        JSONObject jsonSnippet = JSONObject1.getJSONObject("snippet");
                        String videoTitle = jsonSnippet.getString("title");
                        String videoDesc = jsonSnippet.getString("description");
                        String videoThumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                        String videoID = JSONObject1.getJSONObject("id").getString("videoId");
                        meditationCourses.add(new meditation_courses("Sleep", videoTitle, videoDesc, videoThumbnail, videoID));
                        Log.d("TAG", "onPostExecute: videoID " + videoID);
                    }
                }
                meditation_recycler.setAdapter(meditationAdaptor);
                meditation_recycler.setLayoutManager(new LinearLayoutManager(MeditateActivity.this));
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.meditate_recycler).setVisibility(View.VISIBLE);
                //Toast.makeText(MeditateActivity.this, "Video Title: " + videoTitle, Toast.LENGTH_LONG).show();
            }catch (JSONException e){
                Toast.makeText(MeditateActivity.this, "Loading Videos Again", Toast.LENGTH_LONG).show();
            }
        }
    }

    class FocusVideo extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.meditate_recycler).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=Focus+Meditation+Exercise&maxResults=5&key=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray item = jsonObj.getJSONArray("items");
                for (int i = 0; i < item.length() ; i++) {
                    JSONObject JSONObject1= item.getJSONObject(i);
                    JSONObject jsonID = JSONObject1.getJSONObject("id");
                    if (jsonID.getString("kind").equalsIgnoreCase("youtube#video")) {
                        JSONObject jsonSnippet = JSONObject1.getJSONObject("snippet");
                        String videoTitle = jsonSnippet.getString("title");
                        String videoDesc = jsonSnippet.getString("description");
                        String videoThumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                        String videoID = JSONObject1.getJSONObject("id").getString("videoId");
                        meditationCourses.add(new meditation_courses("Focus", videoTitle, videoDesc, videoThumbnail, videoID));
                        Log.d("TAG", "onPostExecute: videoID " + videoID);
                    }
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

    class RelaxVideo extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.meditate_recycler).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=meditation+for+relaxation+and+inner+peace&maxResults=5&key=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray item = jsonObj.getJSONArray("items");
                for (int i = 0; i < item.length() ; i++) {
                    JSONObject JSONObject1= item.getJSONObject(i);
                    JSONObject jsonID = JSONObject1.getJSONObject("id");
                    if (jsonID.getString("kind").equalsIgnoreCase("youtube#video")) {
                        JSONObject jsonSnippet = JSONObject1.getJSONObject("snippet");
                        String videoTitle = jsonSnippet.getString("title");
                        String videoDesc = jsonSnippet.getString("description");
                        String videoThumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                        String videoID = JSONObject1.getJSONObject("id").getString("videoId");
                        meditationCourses.add(new meditation_courses("Relax", videoTitle, videoDesc, videoThumbnail, videoID));
                        Log.d("TAG", "onPostExecute: videoID " + videoID);
                    }
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

    class HappyVideo extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.meditate_recycler).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=meditation+for+happiness+and+positivity&maxResults=5&key=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray item = jsonObj.getJSONArray("items");
                for (int i = 0; i < item.length() ; i++) {
                    JSONObject JSONObject1= item.getJSONObject(i);
                    JSONObject jsonID = JSONObject1.getJSONObject("id");
                    if (jsonID.getString("kind").equalsIgnoreCase("youtube#video")) {
                        JSONObject jsonSnippet = JSONObject1.getJSONObject("snippet");
                        String videoTitle = jsonSnippet.getString("title");
                        String videoDesc = jsonSnippet.getString("description");
                        String videoThumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                        String videoID = JSONObject1.getJSONObject("id").getString("videoId");
                        meditationCourses.add(new meditation_courses("Happiness", videoTitle, videoDesc, videoThumbnail, videoID));
                        Log.d("TAG", "onPostExecute: videoID " + videoID);
                    }
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
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail(), CourseHolder.getVideoId()));
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
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail(),CourseHolder.getVideoId()));
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
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail(),CourseHolder.getVideoId()));
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
                filteredCourses.add(new meditation_courses(CourseHolder.getCategory(), CourseHolder.getTitle(), CourseHolder.getDescription(), CourseHolder.getThumbnail(),CourseHolder.getVideoId()));
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