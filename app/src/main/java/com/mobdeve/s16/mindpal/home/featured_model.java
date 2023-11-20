package com.mobdeve.s16.mindpal.home;

public class featured_model {
    private String Featured_Title;
    private String Featured_Description;
    private String Featured_thumbnail; // URL of the thumbnail image
    private String ArticleUrl; // URL to the full article

    public featured_model(String featured_Title, String featured_Description, String featured_thumbnail, String articleUrl) {
        this.Featured_Title = featured_Title;
        this.Featured_Description = featured_Description;
        this.Featured_thumbnail = featured_thumbnail;
        this.ArticleUrl = articleUrl;
    }

    // Getters
    public String getFeatured_Title() {
        return Featured_Title;
    }

    public String getFeatured_Description() {
        return Featured_Description;
    }

    public String getFeatured_thumbnail() {
        return Featured_thumbnail;
    }

    public String getArticleUrl() {
        return ArticleUrl;
    }

    // Setters
    public void setFeatured_Title(String featured_Title) {
        this.Featured_Title = featured_Title;
    }

    public void setFeatured_Description(String featured_Description) {
        this.Featured_Description = featured_Description;
    }

    public void setFeatured_thumbnail(String featured_thumbnail) {
        this.Featured_thumbnail = featured_thumbnail;
    }

    public void setArticleUrl(String articleUrl) {
        this.ArticleUrl = articleUrl;
    }
}
