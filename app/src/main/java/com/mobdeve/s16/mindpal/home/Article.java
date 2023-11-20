package com.mobdeve.s16.mindpal.home; // Replace with your actual package name

public class Article {
    private String title;
    private String description;
    private String urlToImage;
    private String url; // URL to the full article

    // Constructor
    public Article(String title, String description, String urlToImage, String url) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getUrl() {
        return url;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
