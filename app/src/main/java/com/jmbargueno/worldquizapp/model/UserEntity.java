package com.jmbargueno.worldquizapp.model;

import lombok.Data;

@Data
public class UserEntity {

    private String name, surname, nickname, email;
    private int totalPoints, attemps;


    public double getAverageScore(){
        return totalPoints/attemps;
    }




}
