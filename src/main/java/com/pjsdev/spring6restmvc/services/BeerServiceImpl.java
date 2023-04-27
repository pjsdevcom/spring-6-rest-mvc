package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.Beer;
import com.pjsdev.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Black Sheep")
                .beerStyle(BeerStyle.ALE)
                .upc("123456")
                .price(new BigDecimal("1.80"))
                .quantityOnHand(12)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();
    }
}
