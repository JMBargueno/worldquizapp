package com.salesianostriana.worldquizapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;
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

        service = ServiceGenerator.createService(CountryService.class);
        Call<List<Country>> call = service.getAllCountries();



    }

    @Override
    public void onListFragmentInteraction(Country item) {

    }
}
