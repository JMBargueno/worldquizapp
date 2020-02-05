package com.salesianostriana.worldquizapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Response;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryService;
import com.salesianostriana.worldquizapp.repository.retrofit.ServiceGenerator;
import com.salesianostriana.worldquizapp.service.QuizGenerator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuizzActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    CountryService service;
    Quiz quiz;
    int quizzPoints;
    TextView questionTitle;
    Button optionOne, optionTwo, optionThree;
    Button backOption;
    Button nextOption;
    ProgressBar progressBar;
    int listPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        questionTitle = findViewById(R.id.textViewQuestion);
        optionOne = findViewById(R.id.buttonResponse1);
        optionTwo = findViewById(R.id.buttonResponse2);
        optionThree = findViewById(R.id.buttonResponse3);
        nextOption = findViewById(R.id.buttonNext);
        backOption = findViewById(R.id.buttonBack);
        progressBar = findViewById(R.id.progressBar);

        nextOption.setOnClickListener(this);
        backOption.setOnClickListener(this);

        backOption.setVisibility(View.INVISIBLE);




        service = ServiceGenerator.createService(CountryService.class);
        new CountriesAsyncTask().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNext:
                backOption.setVisibility(View.VISIBLE);
                listPosition++;
                paintView(listPosition);
                break;

            case R.id.buttonBack:
                if (listPosition == 1){
                    backOption.setVisibility(View.INVISIBLE);
                }
                listPosition--;
                paintView(listPosition);
                break;

        }

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
            quiz = quizGenerator.generateQuizz();
            paintView(0);

        }
    }

    private void paintView(int listPosition){

        progressBar.setProgress(listPosition);

        questionTitle.setText(quiz.getQuestionList().get(listPosition).getTitle());
        for (int i = 0; i < 3; i++){
            int randomResponse = ThreadLocalRandom.current().nextInt(0, 2-(i-1));
            switch (i){
                case 0:
                    optionOne.setText(quiz.getQuestionList().get(randomResponse).getTrueResponse().getTitle());
                    break;
                case 1:
                    optionTwo.setText(quiz.getQuestionList().get(randomResponse).getTrueResponse().getTitle());
                    break;
                case 2:
                    optionThree.setText(quiz.getQuestionList().get(randomResponse).getTrueResponse().getTitle());
                    break;
            }

        }

    }

}
