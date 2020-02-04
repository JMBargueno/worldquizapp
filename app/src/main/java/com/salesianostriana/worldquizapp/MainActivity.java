package com.salesianostriana.worldquizapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.salesianostriana.worldquizapp.repository.CountryRepository;
import com.salesianostriana.worldquizapp.repository.CountryService;
import com.salesianostriana.worldquizapp.repository.retrofit.ServiceGenerator;

import com.salesianostriana.worldquizapp.model.Country;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener {

    private Country item;
    private CountryService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_countries, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Al cargar el app se cargan todos los paises en un repositorio
        service = ServiceGenerator.createService(CountryService.class);
        Call<List<Country>> call = service.getAllCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    CountryRepository countryRepository = CountryRepository.getInstance();
                    countryRepository.setListCountry(response.body());


                } else {
                    Toast.makeText(MainActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("Network Failure", t.getMessage());
                Toast.makeText(MainActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onListFragmentInteraction(Country item) {

    }
}
