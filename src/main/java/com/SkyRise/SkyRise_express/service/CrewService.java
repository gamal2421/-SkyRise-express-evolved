package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Crew;

import java.util.List;
import java.util.Optional;

public interface CrewService {

    Crew createCrew(Crew crew);

    Optional<Crew> getCrewById(Long id);

    List<Crew> getAllCrews();

    Crew updateCrew(Crew crew);

    void deleteCrew(Long id);

    // Add other relevant methods based on your business logic
} 