package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.model.Beer;
import com.pjsdev.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beers";
    public static final String BEER_PATH_ID = BEER_PATH + "/{id}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH_ID)
    public Beer getBeerById(@PathVariable UUID id) {

        log.debug("---> called getBeerById in controller");

        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(BEER_PATH)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer) {

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beers/" + savedBeer.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> updateById(@PathVariable UUID id, @RequestBody Beer beer) {

        beerService.updateBeerById(id, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> deleteById(@PathVariable UUID id) {

        beerService.deleteBeerById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> patchById(@PathVariable UUID id, @RequestBody Beer beer) {

        beerService.patchBeerById(id, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
