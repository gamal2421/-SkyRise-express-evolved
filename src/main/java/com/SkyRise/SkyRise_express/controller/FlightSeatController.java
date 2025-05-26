package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightSeat;
import com.SkyRise.SkyRise_express.service.FlightSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flightseats")
public class FlightSeatController {

    private final FlightSeatService flightSeatService;

    @Autowired
    public FlightSeatController(FlightSeatService flightSeatService) {
        this.flightSeatService = flightSeatService;
    }

    @PostMapping
    public ResponseEntity<FlightSeat> createFlightSeat(@RequestBody FlightSeat flightSeat) {
        FlightSeat createdFlightSeat = flightSeatService.createFlightSeat(flightSeat);
        return new ResponseEntity<>(createdFlightSeat, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightSeat> getFlightSeatById(@PathVariable Long id) {
        Optional<FlightSeat> flightSeat = flightSeatService.getFlightSeatById(id);
        return flightSeat.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FlightSeat>> getAllFlightSeats() {
        List<FlightSeat> flightSeats = flightSeatService.getAllFlightSeats();
        return new ResponseEntity<>(flightSeats, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightSeat> updateFlightSeat(@PathVariable Long id, @RequestBody FlightSeat flightSeat) {
        // You might want to add logic here to check if the flightSeat with id exists before updating
        flightSeat.setId(id); // Ensure the correct ID is set for update
        FlightSeat updatedFlightSeat = flightSeatService.updateFlightSeat(flightSeat);
        return ResponseEntity.ok(updatedFlightSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightSeat(@PathVariable Long id) {
        flightSeatService.deleteFlightSeat(id);
        return ResponseEntity.noContent().build();
    }
} 