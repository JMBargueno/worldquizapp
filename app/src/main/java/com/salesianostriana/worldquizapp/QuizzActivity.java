package com.salesianostriana.worldquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Response;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryService;
import com.salesianostriana.worldquizapp.repository.retrofit.ServiceGenerator;
import com.salesianostriana.worldquizapp.service.QuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static android.graphics.BlendMode.COLOR;

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
    String loggedUserId;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    CollectionReference usersRef = db.collection("users");

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Si, quiero salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", null);
        builder.setMessage("¿Está seguro que desea salir?¡Su progreso será borrado!");
        builder.setTitle(R.string.app_name);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        quizzPoints = 0;

        listPosition = 0;

        questionTitle = findViewById(R.id.textViewQuestion);
        optionOne = findViewById(R.id.buttonResponse1);
        optionTwo = findViewById(R.id.buttonResponse2);
        optionThree = findViewById(R.id.buttonResponse3);
        nextOption = findViewById(R.id.buttonNext);
        backOption = findViewById(R.id.buttonBack);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        nextOption.setOnClickListener(this);
        backOption.setOnClickListener(this);

        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);

        backOption.setVisibility(View.INVISIBLE);
        nextOption.setVisibility(View.INVISIBLE);


        service = ServiceGenerator.createService(CountryService.class);
        new CountriesAsyncTask().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.buttonNext:

                backOption.setVisibility(View.VISIBLE);
                listPosition++;
                paintView(listPosition);
                break;

            case R.id.buttonBack:
                if (listPosition == 1) {
                    backOption.setVisibility(View.INVISIBLE);
                }
                listPosition--;
                paintView(listPosition);
                break;*/

            case R.id.buttonResponse1:
                if ((boolean) optionOne.getTag() == true) {

                    //Animacion acertado
                    quizzPoints += 1;
                } else {
                    //Animacion fail
                }
                checkResponse();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listPosition++;
                paintView(listPosition);
                break;

            case R.id.buttonResponse2:
                if ((boolean) optionTwo.getTag() == true) {
                    //Animacion acertado
                    quizzPoints += 1;
                }else{
                    //Animacion fai
                }
                checkResponse();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                listPosition++;
                paintView(listPosition);
                break;
            case R.id.buttonResponse3:
                if ((boolean) optionThree.getTag() == true) {
                    //Animacion acertado
                    quizzPoints += 1;
                } else {
                    //Animacion fail
                }
                checkResponse();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listPosition++;
                paintView(listPosition);
                break;

        }
        Log.i("VALORQUIZZ", Integer.toString(quizzPoints));
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

    private void paintView(int listPosition) {
        optionOne.setBackgroundColor(getResources().getColor(R.color.grayColor));
        optionTwo.setBackgroundColor(getResources().getColor(R.color.grayColor));
        optionThree.setBackgroundColor(getResources().getColor(R.color.grayColor));


        if (listPosition == 5) {


            String currentUserEmail = firebaseUser.getEmail();


            usersRef.whereEqualTo("email", currentUserEmail)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                loggedUserId = task.getResult().getDocuments().get(0).getId();
                                DocumentReference userLoggedReference = usersRef.document(loggedUserId);
                                userLoggedReference.update("totalPoints", FieldValue.increment(quizzPoints));
                                userLoggedReference.update("attemps", FieldValue.increment(1));
                                Log.d("DOCUMENT ID", loggedUserId);
                            } else {
                                Log.d("DOCUMENT ID", loggedUserId, task.getException());
                            }
                        }
                    });


            AlertDialog.Builder builderFinish = new AlertDialog.Builder(this);
            builderFinish.setPositiveButton("¡Vale!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();


                }
            });
            builderFinish.setCancelable(false);
            builderFinish.setMessage("¡Ha ganado " + Integer.toString(quizzPoints) + " puntos!");
            builderFinish.show();



        } else {
            progressBar.setProgress(listPosition + 1);
            questionTitle.setText(quiz.getQuestionList().get(listPosition).getTitle());

            List<Button> buttonList = new ArrayList<>();
            buttonList.add(optionOne);
            buttonList.add(optionTwo);
            buttonList.add(optionThree);
            int randomButton;
            int range;

            for (int i = 0; i < 3; i++) {
                Button button = null;

                switch (i) {
                    case 0:
                        range = (buttonList.size() - 1) + 1 ;
                        randomButton = (int)(Math.random() * range) + 0;

                        button = buttonList.get(randomButton);
                        button.setText(quiz.getQuestionList().get(listPosition).getTrueResponse().getTitle());
                        button.setTag(quiz.getQuestionList().get(listPosition).getTrueResponse().getBooleanValue());
                        buttonList.remove(button);
                        break;
                    case 1:
                        range = (buttonList.size() - 1) + 1 ;
                        randomButton = (int)(Math.random() * range) + 0;
                        button = buttonList.get(randomButton);
                        button.setText(quiz.getQuestionList().get(listPosition).getFailResponse().getTitle());
                        button.setTag(quiz.getQuestionList().get(listPosition).getFailResponse().getBooleanValue());
                        buttonList.remove(button);
                        break;
                    case 2:

                        button = buttonList.get(0);
                        button.setText(quiz.getQuestionList().get(listPosition).getFailResponse2().getTitle());
                        button.setTag(quiz.getQuestionList().get(listPosition).getFailResponse2().getBooleanValue());
                        buttonList.remove(button);
                        break;
                }
            }

        }
        Log.i("LISTPOSITION", Integer.toString(listPosition));

    }


private void checkResponse(){

}

}
