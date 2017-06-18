package com.nminh.flickster;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nminh.flickster.adapters.MovieArrayAdapter;
import com.nminh.flickster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    final String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvMovies.setAdapter(movieAdapter);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(URL, new
                JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // called when response HTTP status is "200 OK"
                        JSONArray movieJsonResults = null;
                        try {
                            movieJsonResults = response.getJSONArray("results");
                            movies.addAll(Movie.fromJSONArray(movieJsonResults));
                            movieAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }

    public boolean isPortraitOrientation(){
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else { // ORIENTATION_LANDSCAPE
            return false;
        }
    }
}
