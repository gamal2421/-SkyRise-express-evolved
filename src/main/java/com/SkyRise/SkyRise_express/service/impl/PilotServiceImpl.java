package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Pilot;
import com.SkyRise.SkyRise_express.repository.PilotRepository;
import com.SkyRise.SkyRise_express.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Pilot createPilot(Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    @Override
    public Optional<Pilot> getPilotById(Long id) {
        return pilotRepository.findById(id);
    }

    @Override
    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    @Override
    public Pilot updatePilot(Pilot pilot) {
        // You might want to add logic here to check if the pilot exists before updating
        return pilotRepository.save(pilot);
    }

    @Override
    public void deletePilot(Long id) {
        pilotRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 