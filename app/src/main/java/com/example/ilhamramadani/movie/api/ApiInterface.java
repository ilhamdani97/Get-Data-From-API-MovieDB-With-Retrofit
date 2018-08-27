package com.example.ilhamramadani.movie.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<Response> popular(@Query("api_key")String apiKey);
    @GET("movie/{id}")
    Call<Response> getMovieDetai(@Path("id")int id,@Query("api_key")String apiKey);
    @GET("movie/now_playing")
    Call<Response> nowplaying(@Query("api_key")String apiKey);
    @GET("movie/upcoming")
    Call<Response> soon(@Query("api_key")String apiKey);
    @GET("search/movie")
    Call<Response> cariFilm(@Query("api_key")String apiKEy,
                            @Query("language")String language,
                            @Query("query") String kataKunci);
}

