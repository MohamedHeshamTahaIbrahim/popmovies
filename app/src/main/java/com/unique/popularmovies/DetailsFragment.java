package com.unique.popularmovies;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    Movie movie;
    Bundle b;

    TextView tvTitle,tvDesc;
    ImageView image;

    View rootView;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            b=getArguments();
            movie= (Movie) b.getSerializable("movie_object");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_details,container,false);

        setUp();

        return rootView;
    }

    private void setUp(){
        tvTitle=(TextView) rootView.findViewById(R.id.tv_movieTitle);
        tvDesc=(TextView) rootView.findViewById(R.id.tv_movieDesc);
        image=(ImageView) rootView.findViewById(R.id.iv_movieImage);


        tvTitle.setText(movie.getTitle());
        tvDesc.setText(movie.getOverview());
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185/"+movie.getImagePath())
                .into(image);
    }
}
