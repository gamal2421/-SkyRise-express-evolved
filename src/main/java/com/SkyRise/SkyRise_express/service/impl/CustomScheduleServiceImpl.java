package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.CustomSchedule;
import com.SkyRise.SkyRise_express.repository.CustomScheduleRepository;
import com.SkyRise.SkyRise_express.service.CustomScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomScheduleServiceImpl implements CustomScheduleService {

    private final CustomScheduleRepository customScheduleRepository;

    @Autowired
    public CustomScheduleServiceImpl(CustomScheduleRepository customScheduleRepository) {
        this.customScheduleRepository = customScheduleRepository;
    }

    @Override
    public CustomSchedule createCustomSchedule(CustomSchedule customSchedule) {
        return customScheduleRepository.save(customSchedule);
    }

    @Override
    public Optional<CustomSchedule> getCustomScheduleById(Long id) {
        return customScheduleRepository.findById(id);
    }

    @Override
    public List<CustomSchedule> getAllCustomSchedules() {
        return customScheduleRepository.findAll();
    }

    @Override
    public CustomSchedule updateCustomSchedule(CustomSchedule customSchedule) {
        // You might want to add logic here to check if the customSchedule exists before updating
        return customScheduleRepository.save(customSchedule);
    }

    @Override
    public void deleteCustomSchedule(Long id) {
        customScheduleRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 