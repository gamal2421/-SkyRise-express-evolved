package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    Airport createAirport(Airport airport);

    Optional<Airport> getAirportById(Long id);

    List<Airport> getAllAirports();

    Airport updateAirport(Airport airport);

    void deleteAirport(Long id);

    // Add other relevant methods based on your business logic
} 