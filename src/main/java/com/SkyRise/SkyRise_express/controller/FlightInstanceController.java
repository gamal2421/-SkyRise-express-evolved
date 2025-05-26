package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.service.FlightInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flightinstances")
public class FlightInstanceController {

    private final FlightInstanceService flightInstanceService;

    @Autowired
    public FlightInstanceController(FlightInstanceService flightInstanceService) {
        this.flightInstanceService = flightInstanceService;
    }

    @PostMapping
    public ResponseEntity<FlightInstance> createFlightInstance(@RequestBody FlightInstance flightInstance) {
        FlightInstance createdFlightInstance = flightInstanceService.createFlightInstance(flightInstance);
        return new ResponseEntity<>(createdFlightInstance, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightInstance> getFlightInstanceById(@PathVariable Long id) {
        Optional<FlightInstance> flightInstance = flightInstanceService.getFlightInstanceById(id);
        return flightInstance.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FlightInstance>> getAllFlightInstances() {
        List<FlightInstance> flightInstances = flightInstanceService.getAllFlightInstances();
        return new ResponseEntity<>(flightInstances, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightInstance> updateFlightInstance(@PathVariable Long id, @RequestBody FlightInstance flightInstance) {
        // You might want to add logic here to check if the flightInstance with id exists before updating
        flightInstance.setId(id); // Ensure the correct ID is set for update
        FlightInstance updatedFlightInstance = flightInstanceService.updateFlightInstance(flightInstance);
        return ResponseEntity.ok(updatedFlightInstance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightInstance(@PathVariable Long id) {
        flightInstanceService.deleteFlightInstance(id);
        return ResponseEntity.noContent().build();
    }
} 