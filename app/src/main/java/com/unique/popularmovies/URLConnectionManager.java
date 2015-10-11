package com.unique.popularmovies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmed on 04/04/2015.
 */
public class URLConnectionManager {

    public static String getMovies() throws IOException {

        BufferedReader reader=null;

        try{
            URL url=new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=e815653aaf44de48c43292d98397de7b");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();

            StringBuilder sb=new StringBuilder();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = reader.readLine()) != null){
                sb.append(line +"\n");
            }
            return  sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
            return  null;

        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
