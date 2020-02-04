package com.salesianostriana.worldquizapp.model;

import lombok.Data;

@Data
public class UserEntity {

    private String name, surname, nickname, email;
    private int totalPoints, attemps;


    private double getAverageScore(){
        return totalPoints/attemps;
    }




}
