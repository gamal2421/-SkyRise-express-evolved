package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
} 