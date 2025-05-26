package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Reservation;
import com.SkyRise.SkyRise_express.model.ReservationStatus;
import com.SkyRise.SkyRise_express.repository.ReservationRepository;
import com.SkyRise.SkyRise_express.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        // Set initial status if not already set
        if (reservation.getStatus() == null) {
            reservation.setStatus(ReservationStatus.CONFIRMED); // Or another initial status
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        // You might want to add logic here to check if the reservation exists before updating
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public boolean cancelReservation(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            // Check if the reservation is already cancelled to avoid unnecessary updates
            if (reservation.getStatus() != ReservationStatus.CANCELLED) {
                reservation.setStatus(ReservationStatus.CANCELLED);
                reservationRepository.save(reservation);
                // In a real application, you might also want to release the seat here
                // by setting the reservation field in the corresponding FlightSeat to null
                return true;
            } else {
                 // Already cancelled
                 return false;
            }
        } else {
            // Reservation not found
            return false;
        }
    }

    // Implement other relevant methods as needed
}