package com.example.ilhamramadani.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ilhamramadani.movie.DetailFilm;
import com.example.ilhamramadani.movie.R;
import com.example.ilhamramadani.movie.api.Response;
import com.example.ilhamramadani.movie.model.AndroidMovie;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MovieViewHolder> {
    private List<AndroidMovie> movies;
    private int rowLayout;
    private Context context;
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        CardView moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView poster;
        Button detail;

        public MovieViewHolder(View view){
            super(view);
            moviesLayout = (CardView)view.findViewById(R.id.movies_layout);
            movieTitle = (TextView)view.findViewById(R.id.title);
            data = (TextView) view.findViewById(R.id.rilis);
            detail = (Button)view.findViewById(R.id.detail_description);
            movieDescription = (TextView) view.findViewById(R.id.description);
            rating = (TextView) view.findViewById(R.id.rating);
            poster = (ImageView) view.findViewById(R.id.poster);

        }
    }
    public DataAdapter(List<AndroidMovie> movies,int rowLayout, Context context){
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + movies.get(position).getPosterPath()).fit().into(holder.poster);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidMovie data = movies.get(position);
                Intent i = new Intent(holder.moviesLayout.getContext(), DetailFilm.class);
                i.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.moviesLayout.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
