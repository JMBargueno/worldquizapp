package com.salesianostriana.worldquizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private String name, surname, email, urlPhoto;
    private int totalPoints, attemps;



    public double getAverageScore(){
        return totalPoints/attemps;
    }




}
