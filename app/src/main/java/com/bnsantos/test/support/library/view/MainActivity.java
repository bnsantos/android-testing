package com.bnsantos.test.support.library.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bnsantos.test.support.library.App;
import com.bnsantos.test.support.library.MovieAdapter;
import com.bnsantos.test.support.library.R;
import com.bnsantos.test.support.library.model.Mode;
import com.bnsantos.test.support.library.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int SETTINGS_REQ_CODE = 111;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Mode mode = Mode.IN_THEATER;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateTitle();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadMovies();
            }
        });

        updateTitle();
        showMovies(mode);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            SettingsActivity.startForResult(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.in_theater){
            showMovies(Mode.IN_THEATER);
        }else if(id == R.id.opening){
            showMovies(Mode.OPENING);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showMovies(Mode mode){
        if(this.mode!=mode){
            adapter.clear();
        }
        this.mode = mode;
        updateTitle();
        reloadMovies();
    }

    private void updateTitle(){
        switch (this.mode){
            case OPENING:
                this.toolbar.setTitle(R.string.movies_opening);
                break;
            default:
                this.toolbar.setTitle(R.string.movies_in_theater);
        }
    }

    private void reloadMovies(){
        Call<MovieResponse> movieCall;
        if(mode==Mode.IN_THEATER){
            movieCall = ((App) getApplication()).getMovieService().inTheater(App.API_KEY);
        }else{
            movieCall = ((App) getApplication()).getMovieService().opening(App.API_KEY);
        }

        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Response<MovieResponse> response) {
                adapter.add(response.body().getMovies());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("BRUNO", "Error", t);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
