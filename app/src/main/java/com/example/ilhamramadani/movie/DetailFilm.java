package com.example.ilhamramadani.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilhamramadani.movie.model.AndroidMovie;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class DetailFilm extends AppCompatActivity {
    ImageView poster,poster1;
    TextView judul,tgl,overview,rating;
    AndroidMovie result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        poster = (ImageView)findViewById(R.id.poster_detail_full);
        poster1 = (ImageView)findViewById(R.id.poster);
        judul = (TextView)findViewById(R.id.detail_title);
        tgl = (TextView)findViewById(R.id.detail_rilis);
        overview = (TextView)findViewById(R.id.detail_description);
        rating = (TextView)findViewById(R.id.detail_rating);
        result = new GsonBuilder().create().fromJson(getIntent().getStringExtra("movie"),AndroidMovie.class);
        Picasso.with(DetailFilm.this).load("https://image.tmdb.org/t/p/w185"+result.getBackdrop_path()).into(poster);
        Picasso.with(DetailFilm.this).load("https://image.tmdb.org/t/p/w185"+result.getPosterPath()).into(poster1);
        judul.setText(result.getTitle());
        rating.setText(Double.toString(result.getVoteAverage()));
        tgl.setText(result.getReleaseDate());
        overview.setText(result.getOverview());
    }
}
