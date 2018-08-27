package com.example.ilhamramadani.movie;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ilhamramadani.movie.adapter.DataAdapter;
import com.example.ilhamramadani.movie.api.ApiClient;
import com.example.ilhamramadani.movie.api.ApiInterface;
import com.example.ilhamramadani.movie.api.Response;
import com.example.ilhamramadani.movie.model.AndroidMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;

public class Search extends AppCompatActivity {
    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String language= "en-US";
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //tool = (Toolbar) findViewById(R.id.toolbar2);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          getSupportActionBar().setTitle("Search Movie For " +getIntent().getStringExtra("Kuery"));
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ActionBar ab = getActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.search_cari);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(),"Api Blom Masuk",Toast.LENGTH_LONG).show();
        }
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiService.cariFilm(API_KEY,language,getIntent().getStringExtra("Kuery"));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int statusCode = response.code();
                List<AndroidMovie> movies = response.body().getResults();
                recyclerView.setAdapter(new DataAdapter(movies,R.layout.card_row,getApplicationContext()));
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
