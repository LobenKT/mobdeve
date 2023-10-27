package com.mobdeve.s16.mindpal;

public class featured_model {
    String Featured_Title;
    String Featured_Description;
    int Featured_thumbnail;

    public featured_model(String featured_Title, String featured_Description, int featured_thumbnail) {
        Featured_Title = featured_Title;
        Featured_Description = featured_Description;
        Featured_thumbnail = featured_thumbnail;
    }

    public String getFeatured_Title() {
        return Featured_Title;
    }

    public String getFeatured_Description() {
        return Featured_Description;
    }

    public int getFeatured_thumbnail() {
        return Featured_thumbnail;
    }

    public void setFeatured_Title(String featured_Title) {
        Featured_Title = featured_Title;
    }

    public void setFeatured_Description(String featured_Description) {
        Featured_Description = featured_Description;
    }

    public void setFeatured_thumbnail(int featured_thumbnail) {
        Featured_thumbnail = featured_thumbnail;
    }
}
