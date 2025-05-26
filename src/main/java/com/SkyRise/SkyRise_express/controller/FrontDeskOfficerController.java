package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FrontDeskOfficer;
import com.SkyRise.SkyRise_express.service.FrontDeskOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/frontdeskofficers")
public class FrontDeskOfficerController {

    private final FrontDeskOfficerService frontDeskOfficerService;

    @Autowired
    public FrontDeskOfficerController(FrontDeskOfficerService frontDeskOfficerService) {
        this.frontDeskOfficerService = frontDeskOfficerService;
    }

    @PostMapping
    public ResponseEntity<FrontDeskOfficer> createFrontDeskOfficer(@RequestBody FrontDeskOfficer frontDeskOfficer) {
        FrontDeskOfficer createdFrontDeskOfficer = frontDeskOfficerService.createFrontDeskOfficer(frontDeskOfficer);
        return new ResponseEntity<>(createdFrontDeskOfficer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrontDeskOfficer> getFrontDeskOfficerById(@PathVariable Long id) {
        Optional<FrontDeskOfficer> frontDeskOfficer = frontDeskOfficerService.getFrontDeskOfficerById(id);
        return frontDeskOfficer.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FrontDeskOfficer>> getAllFrontDeskOfficers() {
        List<FrontDeskOfficer> frontDeskOfficers = frontDeskOfficerService.getAllFrontDeskOfficers();
        return new ResponseEntity<>(frontDeskOfficers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrontDeskOfficer> updateFrontDeskOfficer(@PathVariable Long id, @RequestBody FrontDeskOfficer frontDeskOfficer) {
        // You might want to add logic here to check if the frontDeskOfficer with id exists before updating
        frontDeskOfficer.setId(id); // Ensure the correct ID is set for update
        FrontDeskOfficer updatedFrontDeskOfficer = frontDeskOfficerService.updateFrontDeskOfficer(frontDeskOfficer);
        return ResponseEntity.ok(updatedFrontDeskOfficer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrontDeskOfficer(@PathVariable Long id) {
        frontDeskOfficerService.deleteFrontDeskOfficer(id);
        return ResponseEntity.noContent().build();
    }
} 