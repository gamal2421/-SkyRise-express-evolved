package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CustomSchedule;
import com.SkyRise.SkyRise_express.service.CustomScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customschedules")
public class CustomScheduleController {

    private final CustomScheduleService customScheduleService;

    @Autowired
    public CustomScheduleController(CustomScheduleService customScheduleService) {
        this.customScheduleService = customScheduleService;
    }

    @PostMapping
    public ResponseEntity<CustomSchedule> createCustomSchedule(@RequestBody CustomSchedule customSchedule) {
        CustomSchedule createdCustomSchedule = customScheduleService.createCustomSchedule(customSchedule);
        return new ResponseEntity<>(createdCustomSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomSchedule> getCustomScheduleById(@PathVariable Long id) {
        Optional<CustomSchedule> customSchedule = customScheduleService.getCustomScheduleById(id);
        return customSchedule.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomSchedule>> getAllCustomSchedules() {
        List<CustomSchedule> customSchedules = customScheduleService.getAllCustomSchedules();
        return new ResponseEntity<>(customSchedules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomSchedule> updateCustomSchedule(@PathVariable Long id, @RequestBody CustomSchedule customSchedule) {
        // You might want to add logic here to check if the customSchedule with id exists before updating
        customSchedule.setId(id); // Ensure the correct ID is set for update
        CustomSchedule updatedCustomSchedule = customScheduleService.updateCustomSchedule(customSchedule);
        return ResponseEntity.ok(updatedCustomSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomSchedule(@PathVariable Long id) {
        customScheduleService.deleteCustomSchedule(id);
        return ResponseEntity.noContent().build();
    }
} 