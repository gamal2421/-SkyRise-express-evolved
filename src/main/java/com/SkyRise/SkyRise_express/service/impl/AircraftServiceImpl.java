package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Aircraft;
import com.SkyRise.SkyRise_express.repository.AircraftRepository;
import com.SkyRise.SkyRise_express.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Optional<Aircraft> getAircraftById(Long id) {
        return aircraftRepository.findById(id);
    }

    @Override
    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    @Override
    public Aircraft updateAircraft(Aircraft aircraft) {
        // You might want to add logic here to check if the aircraft exists before updating
        return aircraftRepository.save(aircraft);
    }

    @Override
    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 