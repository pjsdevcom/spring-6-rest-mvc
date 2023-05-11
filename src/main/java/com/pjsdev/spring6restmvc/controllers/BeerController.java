package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.model.Beer;
import com.pjsdev.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("{id}")
    public Beer getBeerById(@PathVariable UUID id) {

        log.debug("---> called getBeerById in controller");

        return beerService.getBeerById(id);
    }

    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @PostMapping
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer) {

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beers/" + savedBeer.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
