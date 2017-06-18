package com.nminh.flickster.models;



import android.content.res.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nminh on 6/17/17.
 */

public class Movie {

    public String getPosterPath(int orientation) {
        String imageSize;
        String imagePath;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            imageSize = "w500";
            imagePath = backdropPath;
        } else { //LANDSCAPE
            imageSize = "w500";
            imagePath = posterPath;
        }
        return String.format("https://image.tmdb.org/t/p/%s/%s", imageSize, imagePath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}