package com.mobdeve.s16.mindpal.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends NavigationActivity {

    TextView WelcomeText;
    ArrayList<featured_model> featuredModels = new ArrayList<>();
    RecyclerView feature_recycler;
    featured_RecyclerAdaptor featureAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNavigation(); // Setup NavBar

        feature_recycler = findViewById(R.id.feature_recycler);
        feature_recycler.setNestedScrollingEnabled(false);

        WelcomeText = findViewById(R.id.Welcome_Message);
        String username = getIntent().getStringExtra("KeyUsername");
        WelcomeText.setText("Welcome, " + username);

        featureAdaptor = new featured_RecyclerAdaptor(this, featuredModels);
        feature_recycler.setAdapter(featureAdaptor);
        feature_recycler.setLayoutManager(new LinearLayoutManager(this));

        loadArticles();
    }

    private void loadArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApiService apiService = retrofit.create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getArticles("meditation+mental health", "f01f2647f6ad40a58c706ed48ddde3c0");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().getArticles();
                    featuredModels.clear(); // Clear existing data
                    for (Article article : articles) {
                        featuredModels.add(new featured_model(
                                article.getTitle(),
                                article.getDescription(),
                                article.getUrlToImage(),
                                article.getUrl() // Assuming Article class has getUrl method
                        ));
                    }
                    featureAdaptor.notifyDataSetChanged(); // Notify adapter about data changes
                } else {
                    // Handle errors
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}