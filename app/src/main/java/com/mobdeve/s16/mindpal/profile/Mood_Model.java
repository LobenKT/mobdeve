package com.mobdeve.s16.mindpal.profile;

public class Mood_Model {
    String Content;
    String Date;

    public Mood_Model(String content, String date) {
        Content = content;
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public String getDate() {
        return Date;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setDate(String date) {
        Date = date;
    }
}
