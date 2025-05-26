package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.WeeklySchedule;
import com.SkyRise.SkyRise_express.service.WeeklyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weeklyschedules")
public class WeeklyScheduleController {

    private final WeeklyScheduleService weeklyScheduleService;

    @Autowired
    public WeeklyScheduleController(WeeklyScheduleService weeklyScheduleService) {
        this.weeklyScheduleService = weeklyScheduleService;
    }

    @PostMapping
    public ResponseEntity<WeeklySchedule> createWeeklySchedule(@RequestBody WeeklySchedule weeklySchedule) {
        WeeklySchedule createdWeeklySchedule = weeklyScheduleService.createWeeklySchedule(weeklySchedule);
        return new ResponseEntity<>(createdWeeklySchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeeklySchedule> getWeeklyScheduleById(@PathVariable Long id) {
        Optional<WeeklySchedule> weeklySchedule = weeklyScheduleService.getWeeklyScheduleById(id);
        return weeklySchedule.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<WeeklySchedule>> getAllWeeklySchedules() {
        List<WeeklySchedule> weeklySchedules = weeklyScheduleService.getAllWeeklySchedules();
        return new ResponseEntity<>(weeklySchedules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeeklySchedule> updateWeeklySchedule(@PathVariable Long id, @RequestBody WeeklySchedule weeklySchedule) {
        // You might want to add logic here to check if the weeklySchedule with id exists before updating
        weeklySchedule.setId(id); // Ensure the correct ID is set for update
        WeeklySchedule updatedWeeklySchedule = weeklyScheduleService.updateWeeklySchedule(weeklySchedule);
        return ResponseEntity.ok(updatedWeeklySchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeeklySchedule(@PathVariable Long id) {
        weeklyScheduleService.deleteWeeklySchedule(id);
        return ResponseEntity.noContent().build();
    }
} 