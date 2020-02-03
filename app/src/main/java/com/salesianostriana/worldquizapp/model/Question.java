package com.salesianostriana.worldquizapp.model;

import lombok.Data;

@Data
public class Question {
    public Country selectedCountry;
    public String title;
    public Response trueResponse, failResponse, failResponse2;
}
