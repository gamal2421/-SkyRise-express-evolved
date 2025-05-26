package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.FrontDeskOfficer;
import com.SkyRise.SkyRise_express.repository.FrontDeskOfficerRepository;
import com.SkyRise.SkyRise_express.service.FrontDeskOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrontDeskOfficerServiceImpl implements FrontDeskOfficerService {

    private final FrontDeskOfficerRepository frontDeskOfficerRepository;

    @Autowired
    public FrontDeskOfficerServiceImpl(FrontDeskOfficerRepository frontDeskOfficerRepository) {
        this.frontDeskOfficerRepository = frontDeskOfficerRepository;
    }

    @Override
    public FrontDeskOfficer createFrontDeskOfficer(FrontDeskOfficer frontDeskOfficer) {
        return frontDeskOfficerRepository.save(frontDeskOfficer);
    }

    @Override
    public Optional<FrontDeskOfficer> getFrontDeskOfficerById(Long id) {
        return frontDeskOfficerRepository.findById(id);
    }

    @Override
    public List<FrontDeskOfficer> getAllFrontDeskOfficers() {
        return frontDeskOfficerRepository.findAll();
    }

    @Override
    public FrontDeskOfficer updateFrontDeskOfficer(FrontDeskOfficer frontDeskOfficer) {
        // You might want to add logic here to check if the frontDeskOfficer exists before updating
        return frontDeskOfficerRepository.save(frontDeskOfficer);
    }

    @Override
    public void deleteFrontDeskOfficer(Long id) {
        frontDeskOfficerRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 