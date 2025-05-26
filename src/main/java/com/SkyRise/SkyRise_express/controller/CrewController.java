package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Crew;
import com.SkyRise.SkyRise_express.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crews")
public class CrewController {

    private final CrewService crewService;

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @PostMapping
    public ResponseEntity<Crew> createCrew(@RequestBody Crew crew) {
        Crew createdCrew = crewService.createCrew(crew);
        return new ResponseEntity<>(createdCrew, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crew> getCrewById(@PathVariable Long id) {
        Optional<Crew> crew = crewService.getCrewById(id);
        return crew.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Crew>> getAllCrews() {
        List<Crew> crews = crewService.getAllCrews();
        return new ResponseEntity<>(crews, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Crew> updateCrew(@PathVariable Long id, @RequestBody Crew crew) {
        // You might want to add logic here to check if the crew with id exists before updating
        crew.setId(id); // Ensure the correct ID is set for update
        Crew updatedCrew = crewService.updateCrew(crew);
        return ResponseEntity.ok(updatedCrew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrew(@PathVariable Long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.noContent().build();
    }
} 