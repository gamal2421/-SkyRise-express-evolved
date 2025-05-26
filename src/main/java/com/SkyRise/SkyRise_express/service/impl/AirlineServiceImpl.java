package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Airline;
import com.SkyRise.SkyRise_express.repository.AirlineRepository;
import com.SkyRise.SkyRise_express.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Optional<Airline> getAirlineById(Long id) {
        return airlineRepository.findById(id);
    }

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline updateAirline(Airline airline) {
        // You might want to add logic here to check if the airline exists before updating
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 