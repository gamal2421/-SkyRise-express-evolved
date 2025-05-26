package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.WeeklySchedule;
import com.SkyRise.SkyRise_express.repository.WeeklyScheduleRepository;
import com.SkyRise.SkyRise_express.service.WeeklyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeeklyScheduleServiceImpl implements WeeklyScheduleService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;

    @Autowired
    public WeeklyScheduleServiceImpl(WeeklyScheduleRepository weeklyScheduleRepository) {
        this.weeklyScheduleRepository = weeklyScheduleRepository;
    }

    @Override
    public WeeklySchedule createWeeklySchedule(WeeklySchedule weeklySchedule) {
        return weeklyScheduleRepository.save(weeklySchedule);
    }

    @Override
    public Optional<WeeklySchedule> getWeeklyScheduleById(Long id) {
        return weeklyScheduleRepository.findById(id);
    }

    @Override
    public List<WeeklySchedule> getAllWeeklySchedules() {
        return weeklyScheduleRepository.findAll();
    }

    @Override
    public WeeklySchedule updateWeeklySchedule(WeeklySchedule weeklySchedule) {
        // You might want to add logic here to check if the weeklySchedule exists before updating
        return weeklyScheduleRepository.save(weeklySchedule);
    }

    @Override
    public void deleteWeeklySchedule(Long id) {
        weeklyScheduleRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 