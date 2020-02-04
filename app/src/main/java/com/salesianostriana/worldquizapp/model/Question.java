package com.salesianostriana.worldquizapp.model;

import lombok.Data;

@Data
public class Question {
    public String title;
    public Country selectedCountry;
    public Response trueResponse, failResponse, failResponse2;
}
