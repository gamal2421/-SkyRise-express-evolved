package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Seat;
import com.SkyRise.SkyRise_express.model.FlightSeat;

import java.util.List;
import java.util.Optional;

public interface SeatService {

    Seat createSeat(Seat seat);

    Optional<Seat> getSeatById(Long id);

    List<Seat> getAllSeats();

    Seat updateSeat(Seat seat);

    void deleteSeat(Long id);

    // Add methods for retrieving seats by aircraft and creating flight seats
    List<Seat> getSeatsByAircraftId(Long aircraftId);
    FlightSeat createFlightSeatForInstance(FlightSeat flightSeat);

    // Add other relevant methods based on your business logic
} 