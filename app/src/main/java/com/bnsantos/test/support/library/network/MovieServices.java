package com.bnsantos.test.support.library.network;

import com.bnsantos.test.support.library.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bruno on 08/01/16.
 */
public interface MovieServices {
    @GET("/in_theaters.json")
    Call<MovieResponse> inTheater(@Query("apiKey") String key);

    @GET("/opening.json")
    Call<MovieResponse> opening(@Query("apiKey") String key);
}
