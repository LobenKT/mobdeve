package com.mobdeve.s16.mindpal.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobdeve.s16.mindpal.notification.Alarm;
import com.mobdeve.s16.mindpal.profile.Mood_Model;
import com.mobdeve.s16.mindpal.profile.goals_model;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 17;
    // Tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MOOD = "mood";
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_ALARMS = "alarms";
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
    // Alarm Table Content
    private static final String ALARM_ID = "alarm_ID";
    private static final String ALARM_USER = "alarm_user";
    private static final String ALARM_TIME = "alarm_time";
    private static final String ALARM_HOUR = "alarm_hour";
    private static final String ALARM_MINUTE = "alarm_minute";
    private static final String ALARM_DATE = "alarm_date";
    private static final String ALARM_REPEAT = "alarm_repeat";
    private static final String ALARM_LABEL = "alarm_label";

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
        if (oldVersion < 17){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
            String CREATE_ALARM_TABLE = "CREATE TABLE " + TABLE_ALARMS + "("
                    + ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ALARM_USER + " INTEGER,"
                    + ALARM_TIME + " TEXT,"
                    + ALARM_HOUR + " INTEGER,"
                    + ALARM_MINUTE + " INTEGER,"
                    + ALARM_DATE + " TEXT,"
                    + ALARM_REPEAT + " TEXT,"
                    + ALARM_LABEL + " TEXT" + ")";
            db.execSQL(CREATE_ALARM_TABLE);
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
    public void addAlarm (int userID, String Time, int hour, int minute, String AlarmDate, String repeat, String Label){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALARM_USER, userID);
        values.put(ALARM_TIME, Time);
        values.put(ALARM_HOUR, hour);
        values.put(ALARM_MINUTE, minute);
        values.put(ALARM_DATE, AlarmDate);
        values.put(ALARM_REPEAT, repeat);
        values.put(ALARM_LABEL, Label);
        db.insert(TABLE_ALARMS, null, values);
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
    public int getAlarmID (int userID, int hour, int minute, String date, String repeat, String label){
        SQLiteDatabase db = getReadableDatabase();
        String[] Columns = {ALARM_ID};
        String userString = String.valueOf(userID);
        String hourString = String.valueOf(hour);
        String minuteString = String.valueOf(minute);
        String selection = ALARM_USER + " = ? AND " + ALARM_HOUR + " = ? AND " + ALARM_MINUTE + " = ? AND " + ALARM_DATE + " = ? AND " + ALARM_REPEAT + " = ? AND " + ALARM_LABEL + " = ? ";
        String[] args = {userString, hourString, minuteString, date, repeat, label};
        Cursor cursor = db.query(TABLE_ALARMS, Columns, selection, args, null, null,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
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
    public ArrayList<Alarm> getAlarms (int userID){
        ArrayList <Alarm> list = new ArrayList<>();
        String id = String.valueOf(userID);
        String[] columns = new String[] {ALARM_ID, ALARM_TIME, ALARM_HOUR, ALARM_MINUTE, ALARM_DATE, ALARM_REPEAT, ALARM_LABEL};
        String selection = ALARM_USER + " = ?";
        String[] selectionArgs = {id};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ALARMS, columns, selection, selectionArgs, null, null, null );
        while (cursor.moveToNext()){
            list.add(new Alarm(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6)));
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

    // Delete Functions
    public void deleteAlarm(int alarmID){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ALARMS + " WHERE " + ALARM_ID + " = " + alarmID;
        db.execSQL(query);
        db.close();
    }

    public void deleteGoal (int goalID){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_GOALS + " WHERE " + GOAL_ID + " = " + goalID;
        db.execSQL(query);
        db.close();
    }

    // Inside DatabaseHelper.java

    public void deleteUserData(int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Convert userID to String for the selection arguments
        String userIdStr = String.valueOf(userID);
        String[] selectionArgs = { userIdStr };

        // Delete user data from the 'users' table
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", selectionArgs);

        // Delete user data from the 'mood' table
        db.delete(TABLE_MOOD, MOOD_USER + " = ?", selectionArgs);

        // Delete user data from the 'goals' table
        db.delete(TABLE_GOALS, GOAL_USER + " = ?", selectionArgs);

        // Delete user data from the 'alarms' table
        db.delete(TABLE_ALARMS, ALARM_USER + " = ?", selectionArgs);

        // Log the deletion
        Log.d("DatabaseHelper", "All data deleted for user ID: " + userID);

        db.close();
    }

}
