package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftService {

    Aircraft createAircraft(Aircraft aircraft);

    Optional<Aircraft> getAircraftById(Long id);

    List<Aircraft> getAllAircraft();

    Aircraft updateAircraft(Aircraft aircraft);

    void deleteAircraft(Long id);

    // Add other relevant methods based on your business logic
} 