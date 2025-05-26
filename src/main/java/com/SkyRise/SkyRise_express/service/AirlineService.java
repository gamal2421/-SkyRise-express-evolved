package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

    Airline createAirline(Airline airline);

    Optional<Airline> getAirlineById(Long id);

    List<Airline> getAllAirlines();

    Airline updateAirline(Airline airline);

    void deleteAirline(Long id);

    // Add other relevant methods based on your business logic
} 