package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.CustomSchedule;

import java.util.List;
import java.util.Optional;

public interface CustomScheduleService {

    CustomSchedule createCustomSchedule(CustomSchedule customSchedule);

    Optional<CustomSchedule> getCustomScheduleById(Long id);

    List<CustomSchedule> getAllCustomSchedules();

    CustomSchedule updateCustomSchedule(CustomSchedule customSchedule);

    void deleteCustomSchedule(Long id);

    // Add other relevant methods based on your business logic
} 