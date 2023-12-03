package com.mobdeve.s16.mindpal.notification;

public class Alarm {

    private int alarm_id;
    private String FormattedTime;
    private int hour;
    private int minute;
    private String date;
    private String repeat;
    private String label;


    public Alarm(int alarm_id, String formattedTime, int hour, int minute, String date, String repeat, String label) {
        this.alarm_id = alarm_id;
        FormattedTime = formattedTime;
        this.hour = hour;
        this.minute = minute;
        this.date = date;
        this.repeat = repeat;
        this.label = label;
    }

    public int getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(int alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getFormattedTime() {
        return FormattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        FormattedTime = formattedTime;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
