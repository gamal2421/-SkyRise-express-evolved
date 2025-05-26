package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.FlightSeat;

import java.util.List;
import java.util.Optional;

public interface FlightSeatService {

    FlightSeat createFlightSeat(FlightSeat flightSeat);

    Optional<FlightSeat> getFlightSeatById(Long id);

    List<FlightSeat> getAllFlightSeats();

    FlightSeat updateFlightSeat(FlightSeat flightSeat);

    void deleteFlightSeat(Long id);

    // Add other relevant methods based on your business logic
} 