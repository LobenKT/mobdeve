package com.mobdeve.s16.mindpal.meditate;

public class meditation_courses {

    String Category;
    String Title;
    String Description;
    int Thumbnail;

    public meditation_courses(String category, String title, String description, int thumbnail) {
        Category = category;
        Title = title;
        Description = description;
        Thumbnail = thumbnail;
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

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
