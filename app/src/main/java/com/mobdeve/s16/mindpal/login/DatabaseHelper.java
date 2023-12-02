package com.mobdeve.s16.mindpal.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobdeve.s16.mindpal.profile.Mood_Model;
import com.mobdeve.s16.mindpal.profile.goals_model;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 13;
    // Tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MOOD = "mood";
    private static final String TABLE_GOALS = "goals";
    //Mood Table Content
    private static final String MOOD_USER = "mood_user";
    private static final String MOOD_ID = "mood_id";
    public static final String MOOD_CONTENT = "mood_content";
    public static final String MOOD_Date = "mood_date";
    //Users Table Content
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IMAGE = "image";
    // Goals Table
    private static final String GOAL_ID = "goal_id";
    private static final String GOAL_USER = "goal_user";
    private static final String GOAL_CONTENT = "goal_content";
    private static final String GOAL_STATUS = "goal_status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT" + ")";
            db.execSQL(CREATE_USERS_TABLE);
        }
        if (oldVersion < 4) {

            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_IMAGE + " TEXT");
        }

        if (oldVersion < 13){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
            String CREATE_MOOD_TABLE = "CREATE TABLE " + TABLE_MOOD + "("
                    + MOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MOOD_USER + " INTEGER,"
                    + MOOD_CONTENT + " TEXT,"
                    + MOOD_Date + " TEXT" + ")";

            db.execSQL(CREATE_MOOD_TABLE);

            String CREATE_GOAL_TABLE = "CREATE TABLE " + TABLE_GOALS + "("
                    + GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GOAL_USER + " INTEGER,"
                    + GOAL_CONTENT + " TEXT,"
                    + GOAL_STATUS + " TEXT" + ")";
            db.execSQL(CREATE_GOAL_TABLE);
        }
    }

    public void addUser(String username, String password, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_IMAGE, image);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addMood (int userID, String content, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOOD_USER, userID);
        values.put(MOOD_CONTENT, content);
        values.put (MOOD_Date, date);
        db.insert(TABLE_MOOD, null, values);
        Log.d("TAG", "Successfully Added Mood: " + userID + content + date);
        db.close();
    }

    public void addGoal (int userID, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GOAL_USER, userID);
        values.put(GOAL_CONTENT, content);
        values.put(GOAL_STATUS, "Ongoing");
        db.insert(TABLE_GOALS, null, values);
        Log.d("TAG", "Successfully Added Goal: " + userID + content);
        db.close();
    }

    public boolean checkedIn (int userID, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String id = String.valueOf(userID);
        Cursor cursor = db.query(TABLE_MOOD, new String[]{MOOD_ID},
                MOOD_USER + "=? AND " + MOOD_Date + "=?",
                new String[]{id, date}, null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }
    public int getUserID (String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] Columns ={COLUMN_ID};
        String selection = COLUMN_USERNAME + " =? AND " + COLUMN_PASSWORD + "=?";
        String[] args = {username, password};
        Cursor cursor = db.query(TABLE_USERS, Columns, selection, args, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);

    }
    public String getMood (int userID, String date){
        String mood = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + MOOD_CONTENT + " FROM " + TABLE_MOOD + " WHERE " + MOOD_USER + " = " + userID +" AND " + MOOD_Date + " = " + "'"+date+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor !=null){
            cursor.moveToFirst();
            mood = cursor.getString(0);
            Log.d("TAG", "Retrieved string: " + mood);
        }
        return mood;
    }

    public ArrayList<Mood_Model> getMood_History (int userID){
        ArrayList<Mood_Model> list = new ArrayList<>();
        String id = String.valueOf(userID);
        String[] columns = new String[]{MOOD_CONTENT , MOOD_Date};
        String selection = MOOD_USER + " = ?";
        String[] selectionArgs = {id};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOOD, columns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()){
            list.add(new Mood_Model(cursor.getString(0), cursor.getString(1) ));
            Log.d("TAG", "Retrieved string: from Cursor " + cursor.getString(0));
        }
        return list;
    }
    public ArrayList<goals_model> getGoals (int userID){
        ArrayList <goals_model> list = new ArrayList<>();
        String id = String.valueOf(userID);
        String[] columns = new String[] {GOAL_ID, GOAL_CONTENT, GOAL_STATUS};
        String selection = GOAL_USER + " = ?";
        String[] selectionArgs = {id};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_GOALS, columns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()){
            list.add(new goals_model(cursor.getInt(0), cursor.getString(1), cursor.getString(2) ));
            Log.d("TAG", "Retrieved string: from Cursor " + cursor.getInt(0) + cursor.getString(1) +cursor.getString(2));
        }
        return list;
    }

    public void updateImage (String image, int userID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, image);
        db.update(TABLE_USERS, contentValues, COLUMN_ID + " = " + userID, null);
        Log.d("TAG", "Retrieved string: " + image);
    }

    public void updateUserName (int userID, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_USERNAME, newName);
        db.update(TABLE_USERS, values,COLUMN_ID + " = " + userID, null );
    }

    public void updatedGoalStatus (int id, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GOAL_STATUS, status);
        db.update(TABLE_GOALS, values, GOAL_ID + " = " + id , null);
    }
    public String getImage(int userID){
        String image = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_IMAGE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = " + userID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor!=null){
            cursor.moveToFirst();
            image = cursor.getString(0);
        }
        return image;
    }
}
