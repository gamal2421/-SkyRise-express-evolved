package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    Passenger createPassenger(Passenger passenger);

    Optional<Passenger> getPassengerById(Long id);

    List<Passenger> getAllPassengers();

    Passenger updatePassenger(Passenger passenger);

    void deletePassenger(Long id);

    // Add other relevant methods based on your business logic
} 