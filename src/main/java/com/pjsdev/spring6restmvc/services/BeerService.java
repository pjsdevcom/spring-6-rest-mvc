package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
