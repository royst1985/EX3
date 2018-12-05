package com.example.roymoria.movies;

public class MovieModel {

    private String name;
    private int imageResourceId;
    private String overview;


    public MovieModel(int imageResourceId, String name,  String overview) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.overview = overview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}

