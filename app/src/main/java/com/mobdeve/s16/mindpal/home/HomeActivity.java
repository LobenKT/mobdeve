package com.mobdeve.s16.mindpal.home;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mobdeve.s16.mindpal.NavigationActivity;
import com.mobdeve.s16.mindpal.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "User");
        WelcomeText.setText("Welcome, " + username);

        TextView inspiringQuote = findViewById(R.id.inspiringQuote);
        new FetchQuoteTask(inspiringQuote).execute();

        featureAdaptor = new featured_RecyclerAdaptor(this, featuredModels);
        feature_recycler.setAdapter(featureAdaptor);
        feature_recycler.setLayoutManager(new LinearLayoutManager(this));

        loadArticles();
    }

    private class FetchQuoteTask extends AsyncTask<Void, Void, String> {
        private TextView textView;

        public FetchQuoteTask(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.api-ninjas.com/v1/quotes?category=life");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("X-Api-Key", "f22659FQ4udk5/UKfTsB4Q==Wd2RPxYKk5ta828u"); // Replace with your API key

                InputStream responseStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                if (jsonArray.length() > 0) {
                    JSONObject firstQuote = jsonArray.getJSONObject(0);
                    return firstQuote.getString("quote");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("FetchQuoteTask", "Error: " + e.getMessage());
            }
            return null;
        }


        @Override
        protected void onPostExecute(String quote) {
            if (quote != null && textView != null) {
                textView.setText(quote);
            } else {
                Log.e("FetchQuoteTask", "Quote is null");
            }
        }
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

                    // Add only the first three articles from the response
                    for (int i = 0; i < articles.size() && i < 3; i++) {
                        Article article = articles.get(i);
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
                Snackbar.make(findViewById(android.R.id.content), "Error loading articles. Try again.", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                loadArticles(); // Retry loading articles
                            }
                        }).show();
            }

        });
    }
}