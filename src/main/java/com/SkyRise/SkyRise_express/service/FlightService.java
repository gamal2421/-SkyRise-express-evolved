package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.FlightInstance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    Flight createFlight(Flight flight);

    Optional<Flight> getFlightById(Long id);

    List<Flight> getAllFlights();

    Flight updateFlight(Flight flight);

    void deleteFlight(Long id);

    List<FlightInstance> searchFlights(LocalDate departureDate, String origin, String destination);

    Optional<FlightInstance> getFlightInstanceByIdWithSeats(Long id);

    // Add other relevant methods based on your business logic
} 