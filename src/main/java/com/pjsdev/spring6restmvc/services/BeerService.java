package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID id, Beer beer);
}
