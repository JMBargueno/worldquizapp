package com.salesianostriana.worldquizapp.service;

import com.salesianostriana.worldquizapp.model.Country;
import com.salesianostriana.worldquizapp.model.Quiz;
import com.salesianostriana.worldquizapp.repository.CountryRepository;

import java.util.List;

public class QuizService {

    CountryRepository countryRepository = CountryRepository.getInstance();
    List<Country> countryList = countryRepository.getListCountry();

    public Quiz generateQuizz(){
        Quiz quiz = new Quiz();



        return quiz;
    }
}
