package com.salesianostriana.worldquizapp.service;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Question;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuizGenerator {

    //CountryRepository countryRepository = CountryRepository.getInstance();
    //List<Country> countryList = countryRepository.getListCountry();
    private List<Country> countryList;

    public QuizGenerator(List<Country> countryList) {
        this.countryList = countryList;
    }

    //Numero de preguntas
    static int QUESTION_NUMBER = 5;
    //Numero de paises en la lista
    static int COUNTRY_LIST_NUMBER = 9;

    //Genera un quizz de 5 preguntas
    public Quiz generateQuizz() {
        Quiz quiz = new Quiz();
        List<Question> questionList = new ArrayList<>();
        List<Country> countryList = generateSelectedCountryList();

        for (int i = 0; i < QUESTION_NUMBER; i++) {

            //Genera preguntas con paises aleatorios
            //Question question = generateQuestionWithRandmonCountries(i);

            //Genera preguntas con una lista dada
            Question question = generateQuestion(i, countryList);
            questionList.add(question);
        }

        quiz.setQuestionList(questionList);


        return quiz;
    }

    //Genera una pregunta aleatoria entre 10 paises aleatorios
   /* private Question generateQuestionWithRandmonCountries(int numType) {
        //Pregunta aleatoria
        //int randomNum = ThreadLocalRandom.current().nextInt(0, 6);

        Question question = new Question(generateSelectedCountryList(), numType);


        return question;
    }*/

    //Genera una pregunta aleatorio entre 10 paises ofrecidos
    private Question generateQuestion(int numType, List<Country> countryList) {
        //Pregunta aleatoria
        //int randomNum = ThreadLocalRandom.current().nextInt(0, 6);

        Question question = new Question(generateSelectedCountryList(), numType, countryList);


        return question;
    }


    //Genera 10 paises aleatorios
    private List<Country> generateSelectedCountryList() {
        List<Country> selectCountries = new ArrayList<>();
        for (int i = 0; i < COUNTRY_LIST_NUMBER; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, countryList.size());
            Country country;
            country = countryList.get(randomNum);
            selectCountries.add(country);
        }

        return selectCountries;
    }


}
