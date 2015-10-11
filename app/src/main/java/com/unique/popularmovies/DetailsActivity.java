package com.unique.popularmovies;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DetailsActivity extends ActionBarActivity {

    Movie m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsFragment frag=new DetailsFragment();

        Bundle bundle=new Bundle();
        m= (Movie) getIntent().getSerializableExtra("movie_object");
        bundle.putSerializable("movie_object",m);
        frag.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.details_container,frag).commit();
    }

}
