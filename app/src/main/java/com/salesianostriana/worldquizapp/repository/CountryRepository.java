package com.salesianostriana.worldquizapp.repository;

import com.salesianostriana.worldquizapp.model.Country;

import java.util.List;

import lombok.Data;

@Data
public class CountryRepository {
    private List<Country> listCountry;
}
