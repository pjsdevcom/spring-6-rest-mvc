package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.model.Beer;
import com.pjsdev.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id) {

        log.debug("---> called getBeerById in controller");

        return beerService.getBeerById(id);
    }
}
