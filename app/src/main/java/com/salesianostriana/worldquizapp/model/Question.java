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

    private List <String> nearCountries;

    public void setNearCountries(List<String> nearCountries) {
        this.nearCountries = nearCountries;
    }
//TODO Implementar el random de paises para que seleccione

    public Question(List<Country> selectedCountryList, int typeOfQuestion) {
        this.selectedCountryList = selectedCountryList;
        this.typeOfQuestion = typeOfQuestion;
        switch (typeOfQuestion){
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
    private void typeOne(){
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es la capital de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response(selectedCountryList.get(0).getCapital(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCapital(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCapital(), false));


    }
    //Nombre de la moneda
    private void typeTwo(){
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cómo se llama la moneda de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response(selectedCountryList.get(0).getCurrencies().get(0).getName(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCurrencies().get(0).getName(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCurrencies().get(0).getName(), false));



    }
    //Simbolo de la moneda
    private void typeThree(){
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el símbolo de la moneda de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response(selectedCountryList.get(0).getCurrencies().get(0).getSymbol(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getCurrencies().get(0).getSymbol(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getCurrencies().get(0).getSymbol(), false));



    }

    //Paises limitrofes
    private void typeFour(){

        List<List<String>> listCountryToSelect = new ArrayList<>();

        List<String> listBorderOne = selectedCountryList.get(0).getBorders();

        List<String> listBorderTwo = selectedCountryList.get(1).getBorders();

        List<String> listBorderThree = selectedCountryList.get(2).getBorders();


        listCountryToSelect.add(listBorderOne);
        listCountryToSelect.add(listBorderTwo);
        listCountryToSelect.add(listBorderThree);




        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el pais limítrofe de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response("Prueba", true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response("Pruba", false));
        this.setFailResponse2(new Response("Prueba", false));




    }
    //Bandera pais
    private void typeFive(){
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es la bandera de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response(selectedCountryList.get(0).getFlag(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getFlag(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getFlag(), false));



    }
    //Idioma pais
    private void typeSix(){
        //Seteamos titulo de la pregunta
        this.setTitle("¿Cuál es el idioma de " + selectedCountryList.get(0).getName() + "?");
        //Respuesta correcta
        this.setTrueResponse( new Response(selectedCountryList.get(0).getLanguages().get(0).getName(), true));
        //Seteamos respuestas incorrectas
        this.setFailResponse(new Response(selectedCountryList.get(1).getLanguages().get(0).getName(), false));
        this.setFailResponse2(new Response(selectedCountryList.get(2).getLanguages().get(0).getName(), false));



    }
    public class getOneBorderCountryAsyncTask extends AsyncTask<List<String>, Void, List<String>> {
        List<String> countryListCode;

        public getOneBorderCountryAsyncTask(List<String> countryListCode) {
            this.countryListCode = countryListCode;
        }

        @Override
        protected List<String> doInBackground(List<String>... lists) {
            String selectedcodeCountry;
            List<String> fullCountryNameList;
            int randomNum = ThreadLocalRandom.current().nextInt(0, countryListCode.size()-1);
            countryListCode.get(randomNum);

            return null;
        }

        @Override
        protected void onPostExecute(List<String> nearCountriesList) {
            setNearCountries(nearCountriesList);
        }
    }

}
