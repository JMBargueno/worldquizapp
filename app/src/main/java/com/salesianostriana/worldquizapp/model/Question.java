package com.salesianostriana.worldquizapp.model;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Question {
    private String title;
    private Country selectedCountry;
    private Response trueResponse, failResponse, failResponse2;
    //Lista de paises seleccionados
    private List<Country> selectedCountryList;
    private int typeOfQuestion;

    //Lista de todos los paises completa
    private List<Country> fullCountryList;


//TODO Implementar el random de paises para que seleccione

    public Question(List<Country> selectedCountryList, int typeOfQuestion, List<Country> fullCountryList) {
        this.selectedCountryList = selectedCountryList;
        this.typeOfQuestion = typeOfQuestion;
        this.fullCountryList = fullCountryList;
        switch (typeOfQuestion) {
            case 0:
                typeOne();
                break;
            case 1:
                typeTwo();
                break;
            case 2:
//                typeThree();
                typeFour();
                break;
            case 3:
                typeFive();
//                typeFour();
                break;
            case 4:
                typeSix();
//                typeFive();
                break;
//            case 5:
//                typeSix();
//                break;

        }
    }


    //Capital de pais
    private void typeOne() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es la capital de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getCapital(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCapital(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCapital(), false));


    }

    //Nombre de la moneda
    private void typeTwo() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cómo se llama la moneda de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getCurrencies().get(0).getName(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCurrencies().get(0).getName(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCurrencies().get(0).getName(), false));


    }

    //Simbolo de la moneda
    private void typeThree() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el símbolo de la moneda de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getCurrencies().get(0).getSymbol(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCurrencies().get(0).getSymbol(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCurrencies().get(0).getSymbol(), false));


    }

    //Paises limitrofes
    private void typeFour() {
        List<List<String>> listCountryCodeToSelect = new ArrayList<>();
        List<String> listOfBorderCountries;

        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderOne = selectedCountryList.get(0).getBorders();
        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderTwo = selectedCountryList.get(1).getBorders();
        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderThree = selectedCountryList.get(2).getBorders();


        //Guardo las listas de iso code en una lista de lista de iso code
        listCountryCodeToSelect.add(listBorderOne);
        listCountryCodeToSelect.add(listBorderTwo);
        listCountryCodeToSelect.add(listBorderThree);

        //Seteo listOfBorderCountries con el metodo
        listOfBorderCountries = getCountryBordersList(listCountryCodeToSelect);


        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el pais limítrofe de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        //Cojo el primer resultado, etc..
        this.setTrueResponse(new Response(listOfBorderCountries.get(0), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(listOfBorderCountries.get(1), false));
        this.setFailResponse2(new Response(listOfBorderCountries.get(2), false));
    }

    //Bandera pais
    private void typeFive() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es la bandera de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getTranslations().getEs(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getTranslations().getEs(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getTranslations().getEs(), false));

    }

    //Idioma pais
    private void typeSix() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el idioma de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getLanguages().get(0).getName(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getLanguages().get(0).getName(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getLanguages().get(0).getName(), false));


    }


    private List<String> getCountryBordersList(List<List<String>> listCountryCodeToSelect) {
        //Lista de paises buscado por isocode
        List<String> searchedCountriesByIsoCode = new ArrayList<>();

        for (List<String> listOfIsoCodeOfOneCountry : listCountryCodeToSelect) {
            if (!listOfIsoCodeOfOneCountry.isEmpty()) {
                int range = (listOfIsoCodeOfOneCountry.size() -1) + 1 ;
                int randomIsoCode = (int)(Math.random() * range) + 0;
                String selectCountryByIsoCode = listOfIsoCodeOfOneCountry.get(randomIsoCode);
                for (Country country : fullCountryList) {

                    if (country.getAlpha3Code().equals(selectCountryByIsoCode)) {
                        searchedCountriesByIsoCode.add(country.getName());
                    }

                }


            } else {
                searchedCountriesByIsoCode.add("Sin fronteras");
            }
        }

        return searchedCountriesByIsoCode;
    }


}
