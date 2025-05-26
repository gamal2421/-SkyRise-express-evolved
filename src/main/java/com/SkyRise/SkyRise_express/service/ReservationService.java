package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Optional<Reservation> getReservationById(Long id);

    List<Reservation> getAllReservations();

    Reservation updateReservation(Reservation reservation);

    void deleteReservation(Long id);

    boolean cancelReservation(Long id);

    // Add other relevant methods based on your business logic
} 