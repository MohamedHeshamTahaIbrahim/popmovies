package com.unique.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 10/05/2015.
 */
public class JSONParser {

    public static List<Movie> parseMovies(String input) throws JSONException {

        List<Movie> movies=new ArrayList<>();
        Movie oneMovie;

        JSONObject root=new JSONObject(input);
        JSONArray data=root.getJSONArray("results");

        for(int i=0;i<data.length();i++){
            oneMovie=new Movie();
            oneMovie.setId(data.getJSONObject(i).getInt("id"));
            oneMovie.setTitle(data.getJSONObject(i).getString("title"));
            oneMovie.setOverview(data.getJSONObject(i).getString("overview"));
            oneMovie.setImagePath(data.getJSONObject(i).getString("poster_path"));
            oneMovie.setReleaseDate(data.getJSONObject(i).getString("release_date"));
            oneMovie.setPopularity(data.getJSONObject(i).getInt("popularity"));
            movies.add(oneMovie);
        }
      return movies;
    }
}
