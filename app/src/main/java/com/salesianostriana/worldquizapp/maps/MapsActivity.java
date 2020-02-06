package com.salesianostriana.worldquizapp.maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.repository.CountryService;
import com.salesianostriana.worldquizapp.ui.country.MyCountryRecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context context;
    CountryService service;
    List<Country> listCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        new CountriesAsyncTask(this).execute();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        for (Country country: listCountries) {
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(country.getLatlng().get(0),country.getLatlng().get(1)))
                    .title("Capital")
                    .snippet("Nº habitantes, idioma")
            );
            m.setTag(country.getAlpha3Code());
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String isoCode = marker.getTag().toString();
                return false;
            }
        });

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
    }

    public class CountriesAsyncTask extends AsyncTask<List<Country>, Void, List<Country>> {

        MapsActivity ctx;

        public CountriesAsyncTask(MapsActivity ctx) {
            this.ctx = ctx;
        }

        @Override
        protected List<Country> doInBackground(List<Country>... lists) {

            Call<List<Country>> call = service.getAllCountries();
            Response<List<Country>> response = null;

            try{
                response = call.execute();

                if(response.isSuccessful()){
                    return response.body();
                }

            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<Country> countries) {

            listCountries = new ArrayList<>(countries);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(ctx);
            Toast.makeText(context, "Countries loaded", Toast.LENGTH_SHORT).show();



        }

    }
}