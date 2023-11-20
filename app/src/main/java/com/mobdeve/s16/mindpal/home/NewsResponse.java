package com.mobdeve.s16.mindpal.home; // Use your appropriate package name

import java.util.List;

public class NewsResponse {
    private List<Article> articles;

    // Constructor
    public NewsResponse(List<Article> articles) {
        this.articles = articles;
    }

    // Getter
    public List<Article> getArticles() {
        return articles;
    }

    // Setter
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
