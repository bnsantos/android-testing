package com.bnsantos.test.support.library.model;

import java.util.Date;

/**
 * Created by bruno on 08/01/16.
 */
public class Movie {
    private String id;
    private String title;
    private int year;
    private String synopsis;
    private ReleaseDate release_dates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ReleaseDate getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(ReleaseDate release_dates) {
        this.release_dates = release_dates;
    }

    public Date getTheaterReleaseDate(){
        if(release_dates!=null&&release_dates.getTheater()!=null){
            return release_dates.getTheater();
        }else{
            return null;
        }
    }
}
