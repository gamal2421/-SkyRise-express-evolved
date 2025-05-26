package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
} 