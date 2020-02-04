package com.salesianostriana.worldquizapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
import com.salesianostriana.worldquizapp.repository.retrofit.ServiceGenerator;
=======
import com.salesianostriana.worldquizapp.model.Country;
>>>>>>> master

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener {

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

        service = ServiceGenerator.createService()


    }

    @Override
    public void onListFragmentInteraction(Country item) {

    }
}
