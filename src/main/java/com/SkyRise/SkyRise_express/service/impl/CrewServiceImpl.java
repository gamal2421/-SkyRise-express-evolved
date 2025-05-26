package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Crew;
import com.SkyRise.SkyRise_express.repository.CrewRepository;
import com.SkyRise.SkyRise_express.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrewServiceImpl implements CrewService {

    private final CrewRepository crewRepository;

    @Autowired
    public CrewServiceImpl(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Override
    public Crew createCrew(Crew crew) {
        return crewRepository.save(crew);
    }

    @Override
    public Optional<Crew> getCrewById(Long id) {
        return crewRepository.findById(id);
    }

    @Override
    public List<Crew> getAllCrews() {
        return crewRepository.findAll();
    }

    @Override
    public Crew updateCrew(Crew crew) {
        // You might want to add logic here to check if the crew exists before updating
        return crewRepository.save(crew);
    }

    @Override
    public void deleteCrew(Long id) {
        crewRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 