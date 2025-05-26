package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Pilot;

import java.util.List;
import java.util.Optional;

public interface PilotService {

    Pilot createPilot(Pilot pilot);

    Optional<Pilot> getPilotById(Long id);

    List<Pilot> getAllPilots();

    Pilot updatePilot(Pilot pilot);

    void deletePilot(Long id);

    // Add other relevant methods based on your business logic
} 