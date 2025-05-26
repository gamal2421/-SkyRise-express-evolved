package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Pilot;
import com.SkyRise.SkyRise_express.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;

    @Autowired
    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PostMapping
    public ResponseEntity<Pilot> createPilot(@RequestBody Pilot pilot) {
        Pilot createdPilot = pilotService.createPilot(pilot);
        return new ResponseEntity<>(createdPilot, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilot> getPilotById(@PathVariable Long id) {
        Optional<Pilot> pilot = pilotService.getPilotById(id);
        return pilot.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pilot>> getAllPilots() {
        List<Pilot> pilots = pilotService.getAllPilots();
        return new ResponseEntity<>(pilots, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pilot> updatePilot(@PathVariable Long id, @RequestBody Pilot pilot) {
        // You might want to add logic here to check if the pilot with id exists before updating
        pilot.setId(id); // Ensure the correct ID is set for update
        Pilot updatedPilot = pilotService.updatePilot(pilot);
        return ResponseEntity.ok(updatedPilot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePilot(@PathVariable Long id) {
        pilotService.deletePilot(id);
        return ResponseEntity.noContent().build();
    }
} 