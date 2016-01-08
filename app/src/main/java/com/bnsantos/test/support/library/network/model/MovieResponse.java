package com.bnsantos.test.support.library.network.model;

import com.bnsantos.test.support.library.model.Movie;

import java.util.List;

/**
 * Created by bruno on 08/01/16.
 */
public class MovieResponse {
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
