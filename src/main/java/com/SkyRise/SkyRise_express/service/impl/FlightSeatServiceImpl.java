package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.FlightSeat;
import com.SkyRise.SkyRise_express.repository.FlightSeatRepository;
import com.SkyRise.SkyRise_express.service.FlightSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    @Autowired
    public FlightSeatServiceImpl(FlightSeatRepository flightSeatRepository) {
        this.flightSeatRepository = flightSeatRepository;
    }

    @Override
    public FlightSeat createFlightSeat(FlightSeat flightSeat) {
        return flightSeatRepository.save(flightSeat);
    }

    @Override
    public Optional<FlightSeat> getFlightSeatById(Long id) {
        return flightSeatRepository.findById(id);
    }

    @Override
    public List<FlightSeat> getAllFlightSeats() {
        return flightSeatRepository.findAll();
    }

    @Override
    public FlightSeat updateFlightSeat(FlightSeat flightSeat) {
        // You might want to add logic here to check if the flightSeat exists before updating
        return flightSeatRepository.save(flightSeat);
    }

    @Override
    public void deleteFlightSeat(Long id) {
        flightSeatRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 