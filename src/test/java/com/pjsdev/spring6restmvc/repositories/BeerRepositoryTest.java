package com.pjsdev.spring6restmvc.repositories;

import com.pjsdev.spring6restmvc.entities.Beer;
import com.pjsdev.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("Corona").beerStyle(BeerStyle.LAGER).upc("123123123").price(new BigDecimal("2.49"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            beerRepository.save(Beer.builder()
                .beerName("CoronaCoronaCoronaCoronaCoronaCoronaCoronaCoronaCorona")
                .beerStyle(BeerStyle.LAGER).upc("123123123").price(new BigDecimal("2.49"))
                .build());

            beerRepository.flush();
        });
    }
}