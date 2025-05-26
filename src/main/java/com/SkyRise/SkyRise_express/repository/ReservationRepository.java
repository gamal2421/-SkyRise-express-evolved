package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
} 