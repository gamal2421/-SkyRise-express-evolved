package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.FrontDeskOfficer;

import java.util.List;
import java.util.Optional;

public interface FrontDeskOfficerService {

    FrontDeskOfficer createFrontDeskOfficer(FrontDeskOfficer frontDeskOfficer);

    Optional<FrontDeskOfficer> getFrontDeskOfficerById(Long id);

    List<FrontDeskOfficer> getAllFrontDeskOfficers();

    FrontDeskOfficer updateFrontDeskOfficer(FrontDeskOfficer frontDeskOfficer);

    void deleteFrontDeskOfficer(Long id);

    // Add other relevant methods based on your business logic
} 