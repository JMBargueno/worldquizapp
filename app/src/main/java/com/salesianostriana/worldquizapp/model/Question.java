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
    private List<Country> selectedCountryList;
    private int typeOfQuestion;
    private List<Country> fullCountryList;

    private List<String> nearCountries;

    public void setNearCountries(List<String> nearCountries) {
        this.nearCountries = nearCountries;
    }
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
                typeThree();
                break;
            case 3:
                typeFour();
                break;
            case 4:
                typeFive();
                break;
            case 5:
                typeSix();
                break;

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
        List<String> emptyList = new ArrayList<>();
        List<List<String>> listCountryCodeToSelect = new ArrayList<>();

        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderOne = selectedCountryList.get(0).getBorders().isEmpty() ? emptyList : selectedCountryList.get(0).getBorders();
        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderTwo = selectedCountryList.get(1).getBorders().isEmpty() ? emptyList : selectedCountryList.get(1).getBorders();
        //Si la lista esta vacia, le setea una vacia
        List<String> listBorderThree = selectedCountryList.get(2).getBorders().isEmpty() ? emptyList : selectedCountryList.get(2).getBorders();


        //Guardo las listas de iso code en una lista de lista de iso code
        listCountryCodeToSelect.add(listBorderOne);
        listCountryCodeToSelect.add(listBorderTwo);
        listCountryCodeToSelect.add(listBorderThree);

        //Seteo nearCountries con el metodo
        this.nearCountries = getCountryBordersList(listCountryCodeToSelect);


        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el pais limítrofe de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        //Cojo el primer resultado, etc..
        this.setTrueResponse(new Response(nearCountries.get(0), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(nearCountries.get(1), false));
        this.setFailResponse2(new Response(nearCountries.get(2), false));
    }

    //Bandera pais
    private void typeFive() {
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es la bandera de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse(new Response(selectedCountryList.get(0).getFlag(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getFlag(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getFlag(), false));

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
        String selectIsoCode;
        String selectedCountry;
        List<String> searchedCountries = new ArrayList<>();

        //Recorro una lista de la lista
        for (List<String> listBordersIsoCode : listCountryCodeToSelect) {
            //Si la lista no esta vacia
            if (!listBordersIsoCode.isEmpty()) {
                //Cojo un random
                int randomIsoCode = ThreadLocalRandom.current().nextInt(0, listBordersIsoCode.size() - 1);
                //Selecciono un iso code de la lista de mamera random
                selectIsoCode = listBordersIsoCode.get(randomIsoCode);

                //Recorro cada pais de la lista entera de paises
                for (Country country : fullCountryList) {
                    //Si el pais tiene el mismo iso code que el iso code seleccionado de manera random
                    if (country.getAlpha3Code() == selectIsoCode) {
                        //Cojo su nombre
                        selectedCountry = country.getName();
                        // Y lo añado a la lista
                        searchedCountries.add(selectedCountry);
                    }
                }
                //Si esta vacia, añado "Ninguno"
            } else {
                searchedCountries.add("Ninguno");
            }
        }
        //Devuelvo la lista de string con el nombre de los paises
        return searchedCountries;
    }


}
