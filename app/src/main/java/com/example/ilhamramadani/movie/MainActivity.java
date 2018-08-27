package com.example.ilhamramadani.movie;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilhamramadani.movie.adapter.DataAdapter;
import com.example.ilhamramadani.movie.adapter.FragmentAdapter;
import com.example.ilhamramadani.movie.api.ApiClient;
import com.example.ilhamramadani.movie.api.ApiInterface;
import com.example.ilhamramadani.movie.api.Response;
import com.example.ilhamramadani.movie.fragment.FragmentNowplaying;
import com.example.ilhamramadani.movie.fragment.FragmentPopular;
import com.example.ilhamramadani.movie.fragment.FragmentUpcoming;
import com.example.ilhamramadani.movie.model.AndroidMovie;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String language= "en-US";
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private Button detail;
    private Button view;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentNowplaying(),"");
        adapter.AddFragment(new FragmentPopular(),"");
        adapter.AddFragment(new FragmentUpcoming(),"");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.nowplaying);
        tabLayout.getTabAt(1).setIcon(R.drawable.popular);
        tabLayout.getTabAt(2).setIcon(R.drawable.upcoming);
        actionBar.setElevation(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                Intent Search = new Intent(MainActivity.this,Search.class);
                Search.putExtra("Kuery",query);
                startActivity(Search);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.English:
                setLocale("");
                recreate();
                return true;
            case R.id.Bahasa:
                setLocale("in");
                recreate();
                return true;

                default:
                    return true;
        }
    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Seting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Seting", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }

}
