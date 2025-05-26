package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.WeeklySchedule;

import java.util.List;
import java.util.Optional;

public interface WeeklyScheduleService {

    WeeklySchedule createWeeklySchedule(WeeklySchedule weeklySchedule);

    Optional<WeeklySchedule> getWeeklyScheduleById(Long id);

    List<WeeklySchedule> getAllWeeklySchedules();

    WeeklySchedule updateWeeklySchedule(WeeklySchedule weeklySchedule);

    void deleteWeeklySchedule(Long id);

    // Add other relevant methods based on your business logic
} 