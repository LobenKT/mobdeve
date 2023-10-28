package com.mobdeve.s16.mindpal.notification;

public class Alarm {
    private String time;
    private String repeat;
    private String label;

    public Alarm(String time, String repeat, String label) {
        this.time = time;
        this.repeat = repeat;
        this.label = label;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
