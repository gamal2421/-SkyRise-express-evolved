package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.repository.FlightRepository;
import com.SkyRise.SkyRise_express.repository.FlightInstanceRepository;
import com.SkyRise.SkyRise_express.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightInstanceRepository flightInstanceRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, FlightInstanceRepository flightInstanceRepository) {
        this.flightRepository = flightRepository;
        this.flightInstanceRepository = flightInstanceRepository;
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(Flight flight) {
        // You might want to add logic here to check if the flight exists before updating
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightInstance> searchFlights(LocalDate departureDate, String origin, String destination) {
        // Basic implementation for now, we'll add actual search logic later
        // This should ideally query FlightInstance based on date and linked Flight's origin/destination
        return Collections.emptyList();
    }

    @Override
    public Optional<FlightInstance> getFlightInstanceByIdWithSeats(Long id) {
        // Basic implementation for now, we'll add actual logic to fetch with seats later
        return flightInstanceRepository.findById(id);
    }

    // Implement other relevant methods as needed
}