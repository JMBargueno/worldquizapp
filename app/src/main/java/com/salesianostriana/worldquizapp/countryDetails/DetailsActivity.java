package com.salesianostriana.worldquizapp.countryDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.model.unsplash.Result;

public class DetailsActivity extends AppCompatActivity implements ResultFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void onListFragmentInteraction(Result item) {

    }
}
