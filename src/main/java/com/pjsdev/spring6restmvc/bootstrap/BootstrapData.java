package com.pjsdev.spring6restmvc.bootstrap;

import com.pjsdev.spring6restmvc.entities.Beer;
import com.pjsdev.spring6restmvc.entities.Customer;
import com.pjsdev.spring6restmvc.model.BeerStyle;
import com.pjsdev.spring6restmvc.repositories.BeerRepository;
import com.pjsdev.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        if (beerRepository.count() == 0 && customerRepository.count() == 0) {
            Beer beer1 = Beer.builder()
                    .beerName("San Miguel")
                    .beerStyle(BeerStyle.LAGER)
                    .price(new BigDecimal("2.50"))
                    .quantityOnHand(50)
                    .upc("123890567")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();
            Beer beer2 = Beer.builder()
                    .beerName("Guinness")
                    .beerStyle(BeerStyle.STOUT)
                    .price(new BigDecimal("2.20"))
                    .quantityOnHand(40)
                    .upc("456890234")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();
            Beer beer3 = Beer.builder()
                    .beerName("Heineken")
                    .beerStyle(BeerStyle.LAGER)
                    .price(new BigDecimal("2.30"))
                    .quantityOnHand(40)
                    .upc("789456234")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();

            beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3));

            Customer customer1 = Customer.builder()
                    .name("John White")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();
            Customer customer2 = Customer.builder()
                    .name("Chris White")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();
            Customer customer3 = Customer.builder()
                    .name("Bobby White")
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }
    }
}
