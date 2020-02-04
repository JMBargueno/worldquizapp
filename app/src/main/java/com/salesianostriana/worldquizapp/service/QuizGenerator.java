package com.salesianostriana.worldquizapp.service;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Question;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuizGenerator {

    CountryRepository countryRepository = CountryRepository.getInstance();
    List<Country> countryList = countryRepository.getListCountry();

    public Quiz generateQuizz(){
        Quiz quiz = new Quiz();
        Country countryOfQuestion;
        int randomNum = ThreadLocalRandom.current().nextInt(0, countryList.size());
        countryOfQuestion = countryList.get(randomNum);




        return quiz;
    }

    public Question generateQuestion(Country country){
        Question question = new Question();
        return question;
    }


}
