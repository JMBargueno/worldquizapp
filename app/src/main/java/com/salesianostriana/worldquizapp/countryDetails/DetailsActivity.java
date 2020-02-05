package com.salesianostriana.worldquizapp.countryDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.model.unsplash.Result;

public class DetailsActivity extends AppCompatActivity implements ResultFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView imageViewPeople = findViewById(R.id.imageViewPeople);
        TextView txtPeople = findViewById(R.id.textViewPeople);

        Glide
                .with(this)
                .load(R.drawable.ic_people)
                .into(imageViewPeople);

        txtPeople.setText(getIntent().getExtras().get("peopleCountry").toString() + " habitantes");


    }

    @Override
    public void onListFragmentInteraction(Result item) {

    }
}
