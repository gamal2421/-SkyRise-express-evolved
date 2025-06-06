package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
} 