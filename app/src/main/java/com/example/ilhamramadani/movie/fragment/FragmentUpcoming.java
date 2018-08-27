package com.example.ilhamramadani.movie.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ilhamramadani.movie.BuildConfig;
import com.example.ilhamramadani.movie.R;
import com.example.ilhamramadani.movie.adapter.DataAdapter;
import com.example.ilhamramadani.movie.api.ApiClient;
import com.example.ilhamramadani.movie.api.ApiInterface;
import com.example.ilhamramadani.movie.api.Response;
import com.example.ilhamramadani.movie.model.AndroidMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;
import static com.example.ilhamramadani.movie.BuildConfig.API_KEY;

public class FragmentUpcoming extends android.support.v4.app.Fragment {
    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String language= "en-US";
    private RecyclerView recyclerView;
    View view;
    public FragmentUpcoming() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.upcoming_fragment,container,false);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.soon_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (API_KEY.isEmpty()){
            Toast.makeText(getContext(),"Api Blom Masuk",Toast.LENGTH_LONG).show();

        }
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiService.soon(API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int statusCode = response.code();
                List<AndroidMovie> movies = response.body().getResults();
                recyclerView.setAdapter(new DataAdapter(movies,R.layout.card_row,getContext()));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }
}
