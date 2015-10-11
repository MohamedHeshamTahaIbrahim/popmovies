package com.unique.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 05/09/2015.
 */
public class CustomGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<Movie> movies=new ArrayList<>();

    private Context context;

    public CustomGridAdapter(Context c , List<Movie> comingMovies)
    {
        inflater = LayoutInflater.from(c);

        this.context=c;

        this.movies=comingMovies;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = view;
        ImageView picture;

        if(v == null)
        {
            v = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        picture = (ImageView)v.findViewById(R.id.picture);

        String imageUrl="http://image.tmdb.org/t/p/w185/"+movies.get(i).getImagePath();

        Picasso.with(context).load(imageUrl).into(picture);

        return v;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i)
    {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return movies.get(i).getId();
    }
}
