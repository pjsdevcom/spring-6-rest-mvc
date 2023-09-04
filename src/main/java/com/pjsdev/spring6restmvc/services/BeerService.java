package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    List<BeerDTO> listBeers();

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);

    Boolean deleteBeerById(UUID id);

    void patchBeerById(UUID id, BeerDTO beer);
}
