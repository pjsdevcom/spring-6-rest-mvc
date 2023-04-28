package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.Beer;
import com.pjsdev.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("---> called getBeerById in service");

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
