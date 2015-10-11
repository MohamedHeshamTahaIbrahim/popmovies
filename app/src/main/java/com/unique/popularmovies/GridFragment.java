package com.unique.popularmovies;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 05/09/2015.
 */
public class GridFragment extends Fragment {

    private View rootView;
    private ProgressBar pb;
    private GridView gv;
    private CustomGridAdapter adapter;

    private List<Movie> movies=new ArrayList<>();

    public GridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.grid_fragment, container, false);

        setup();

        getDate();

        return rootView;
    }

    private void setup(){
        gv = (GridView) rootView.findViewById(R.id.gv);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent(getActivity(),DetailsActivity.class);
                i.putExtra("movie_object",movies.get(position));
                startActivity(i);
                 }
        });

        pb=(ProgressBar) rootView.findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);

    }

    private void getDate(){
        if(isOnLine()){
            MyTask task=new MyTask();
            task.execute();
        }else{
            Toast.makeText(getActivity(),"No Internet Access",Toast.LENGTH_SHORT).show();
        }
    }


    private class MyTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String content = null;
            try {

                content = URLConnectionManager.getMovies();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            pb.setVisibility(View.INVISIBLE);
            if(s == null){
                Toast.makeText(getActivity(),"Error in online data",Toast.LENGTH_LONG).show();
                return;
            }
            try {
                movies=JSONParser.parseMovies(s);
                if(movies.size() > 0){

                    adapter = new CustomGridAdapter(getActivity(),movies);
                    gv.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getActivity(),"No data",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean isOnLine() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
