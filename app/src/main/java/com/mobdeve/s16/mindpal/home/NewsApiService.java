package com.mobdeve.s16.mindpal.home;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("v2/everything")
    Call<NewsResponse> getArticles(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
