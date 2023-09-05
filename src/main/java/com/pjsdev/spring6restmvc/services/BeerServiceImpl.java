package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.BeerDTO;
import com.pjsdev.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, BeerDTO> beerMap = new HashMap<>();

    public BeerServiceImpl() {
        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Black Sheep")
                .beerStyle(BeerStyle.ALE)
                .upc("123456")
                .price(new BigDecimal("1.80"))
                .quantityOnHand(12)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Kronenbourg 1664")
                .beerStyle(BeerStyle.LAGER)
                .upc("234567")
                .price(new BigDecimal("2.10"))
                .quantityOnHand(24)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Brewdog Black Heart")
                .beerStyle(BeerStyle.STOUT)
                .upc("345678")
                .price(new BigDecimal("2.30"))
                .quantityOnHand(40)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("---> called getBeerById in service");

        return Optional.of(beerMap.get(id));
    }

    @Override
    public List<BeerDTO> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {

        BeerDTO existingBeer = beerMap.get(id);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setDateUpdated(LocalDateTime.now());

        return Optional.of(existingBeer);
    }

    @Override
    public Boolean deleteBeerById(UUID id) {
        return beerMap.remove(id) != null;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID id, BeerDTO beer) {

        BeerDTO existingBeer = beerMap.get(id);

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }
        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }
        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }
        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }

        return Optional.of(existingBeer);
    }
}
