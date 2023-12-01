package com.mobdeve.s16.mindpal.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 10;
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MOOD = "mood";
    private static final String MOOD_USERNAME = "mood_username";
    private static final String MOOD_ID = "mood_id";
    private static final String MOOD_CONTENT = "mood_content";
    private static final String MOOD_Date = "mood_date";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IMAGE = "image";

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
        if (oldVersion < 9) {
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD);
            String CREATE_MOOD_TABLE = "CREATE TABLE " + TABLE_MOOD + "("
                    + MOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MOOD_USERNAME + "TEXT,"
                    + MOOD_CONTENT + " TEXT,"
                    + MOOD_Date + " TEXT" + ")";

            db.execSQL(CREATE_MOOD_TABLE);
        }
        if (oldVersion < 10){
            db.execSQL("ALTER TABLE " + TABLE_MOOD + " ADD COLUMN " + MOOD_USERNAME + " TEXT");
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

    public void addMood (String username, String content, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOOD_USERNAME, username);
        values.put(MOOD_CONTENT, content);
        values.put (MOOD_Date, date);
        db.insert(TABLE_MOOD, null, values);
        Log.d("TAG", "Successfully Added Mood: " + username + content + date);
        db.close();
    }

    public boolean checkedIn (String username, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOOD, new String[]{MOOD_ID},
                MOOD_USERNAME + "=? AND " + MOOD_Date + "=?",
                new String[]{username, date}, null, null, null);

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
    public String getMood (String username, String date){
        String mood = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + MOOD_CONTENT + " FROM " + TABLE_MOOD + " WHERE " + MOOD_USERNAME + " = " + "'"+username+"' AND " + MOOD_Date + " = " + "'"+date+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor !=null){
            cursor.moveToFirst();
            mood = cursor.getString(0);
            Log.d("TAG", "Retrieved string: " + mood);
        }
        return mood;
    }

    public void updateImage (String image, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, image);
        db.update(TABLE_USERS, contentValues, COLUMN_USERNAME + " = " + "'"+name+"'", null);
        Log.d("TAG", "Retrieved string: " + image);
    }

    public void updateUserName (String name, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_USERNAME, newName);
        db.update(TABLE_USERS, values,COLUMN_USERNAME + " = " + "'"+name+"'", null );
    }
    public String getImage(String name){
        String image = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_IMAGE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = " + "'"+name+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor!=null){
            cursor.moveToFirst();
            image = cursor.getString(0);
            Log.d("TAG", "Retrieved string: " + name);
        }
        return image;
    }
}
