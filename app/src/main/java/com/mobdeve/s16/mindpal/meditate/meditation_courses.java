package com.mobdeve.s16.mindpal.meditate;

public class meditation_courses {

    String Category;
    String Title;
    String Description;
    String Thumbnail;

    String VideoId;

    public meditation_courses(String category, String title, String description, String thumbnail, String videoId) {
        Category = category;
        Title = title;
        Description = description;
        Thumbnail = thumbnail;
        VideoId = videoId;
    }

    public String getCategory() {
        return Category;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getThumbnail() {
        return Thumbnail;
    }
    public String getVideoId(){return VideoId;}

    public void setCategory(String category) {
        Category = category;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
    public void setVideoId(String videoId){VideoId = videoId;}
}
