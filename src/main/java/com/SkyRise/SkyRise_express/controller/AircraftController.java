package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Aircraft;
import com.SkyRise.SkyRise_express.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft) {
        Aircraft createdAircraft = aircraftService.createAircraft(aircraft);
        return new ResponseEntity<>(createdAircraft, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        Optional<Aircraft> aircraft = aircraftService.getAircraftById(id);
        return aircraft.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAllAircraft() {
        List<Aircraft> aircraft = aircraftService.getAllAircraft();
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        // You might want to add logic here to check if the aircraft with id exists before updating
        aircraft.setId(id); // Ensure the correct ID is set for update
        Aircraft updatedAircraft = aircraftService.updateAircraft(aircraft);
        return ResponseEntity.ok(updatedAircraft);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
} 