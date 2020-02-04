package com.salesianostriana.worldquizapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Response;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryService;
import com.salesianostriana.worldquizapp.repository.retrofit.ServiceGenerator;
import com.salesianostriana.worldquizapp.service.QuizGenerator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {
    Context context;
    CountryService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);


        service = ServiceGenerator.createService(CountryService.class);
        new CountriesAsyncTask().execute();

    }

    public class CountriesAsyncTask extends AsyncTask<List<Country>, Void, List<Country>> {

        @Override
        protected List<Country> doInBackground(List<Country>... lists) {

            Call<List<Country>> call = service.getAllCountries();
            Response<List<Country>> response = null;

            try {
                response = call.execute();

                if (response.isSuccessful()) {
                    return response.body();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<Country> countries) {
            QuizGenerator quizGenerator = new QuizGenerator(countries);
            Quiz quizz = quizGenerator.generateQuizz();


        }
    }
}
