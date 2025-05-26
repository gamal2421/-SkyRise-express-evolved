package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Seat;
import com.SkyRise.SkyRise_express.model.FlightSeat;
import com.SkyRise.SkyRise_express.repository.SeatRepository;
import com.SkyRise.SkyRise_express.repository.FlightSeatRepository;
import com.SkyRise.SkyRise_express.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final FlightSeatRepository flightSeatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, FlightSeatRepository flightSeatRepository) {
        this.seatRepository = seatRepository;
        this.flightSeatRepository = flightSeatRepository;
    }

    @Override
    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Optional<Seat> getSeatById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat updateSeat(Seat seat) {
        // You might want to add logic here to check if the seat exists before updating
        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public List<Seat> getSeatsByAircraftId(Long aircraftId) {
        // Assuming SeatRepository has a method to find seats by aircraft ID
        return seatRepository.findByAircraftId(aircraftId); // Assuming findByAircraftId method exists
    }

    @Override
    @Transactional // Ensure saving FlightSeat is part of a transaction
    public FlightSeat createFlightSeatForInstance(FlightSeat flightSeat) {
        return flightSeatRepository.save(flightSeat);
    }

    // Implement other relevant methods as needed
} 