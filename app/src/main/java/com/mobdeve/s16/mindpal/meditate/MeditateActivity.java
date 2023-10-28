package com.mobdeve.s16.mindpal.meditate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

import java.util.ArrayList;

public class MeditateActivity extends NavigationActivity {

    ArrayList<meditation_courses> meditationCourses = new ArrayList<>();
    ArrayList<meditation_courses> filteredCourses = new ArrayList<>();
    meditation_courses CourseHolder;
    RecyclerView meditation_recycler;
    meditation_Adaptor meditationAdaptor;

    Button sleep_btn , focus_btn, relax_btn, happy_btn, reset_btn;

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

        meditationCourses.add(new meditation_courses("Sleep", "Sleep Meditation", "You Feel Sleep now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Sleep", "Sleep Meditation2", "You Feel Sleep now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Focus", "Focus Meditation", "You Feel Focus now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Focus", "Focus Meditation", "You Feel Focus now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Relax", "Relax Meditation", "You Feel Relax now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Relax", "Relax Meditation", "You Feel Relax now fr", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Happiness", "Happy Meditation", "You Feel Happy now", R.drawable.sample_thumbnail));
        meditationCourses.add(new meditation_courses("Happiness", "Happy Meditation", "You Feel Happy now fr", R.drawable.sample_thumbnail));

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