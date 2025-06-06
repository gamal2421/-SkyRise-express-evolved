package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
} 