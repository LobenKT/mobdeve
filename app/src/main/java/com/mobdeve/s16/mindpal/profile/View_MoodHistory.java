package com.mobdeve.s16.mindpal.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.mobdeve.s16.mindpal.R;
import com.mobdeve.s16.mindpal.login.DatabaseHelper;

import java.util.ArrayList;

public class View_MoodHistory extends AppCompatActivity {
    DatabaseHelper dbHelper;
    RecyclerView mood_recycler;
    Mood_RecyclerAdaptor moodAdaptor;
    private SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mood_history);

        mood_recycler = (RecyclerView) findViewById(R.id.mood_recycler);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        int userID = sharedPreferences.getInt("ID", 0);
        dbHelper = new DatabaseHelper(this);
        ArrayList<Mood_Model> moodModels = dbHelper.getMood_History(userID);
        moodAdaptor = new Mood_RecyclerAdaptor(this, moodModels);
        mood_recycler.setAdapter(moodAdaptor);
        mood_recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}