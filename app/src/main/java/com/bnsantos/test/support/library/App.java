package com.bnsantos.test.support.library;

import android.app.Application;

import com.bnsantos.test.support.library.network.MovieService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by bruno on 08/01/16.
 */
public class App extends Application {
    public static String API_KEY = "365navjkfsvx3872nq8wvyhf";

    private MovieService movieService;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(" http://api.rottentomatoes.com/api/public/v1.0/lists/movies/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    public MovieService getMovieService() {
        return movieService;
    }
}
