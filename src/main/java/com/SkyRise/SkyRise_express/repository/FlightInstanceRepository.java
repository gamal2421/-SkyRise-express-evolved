package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInstanceRepository extends JpaRepository<FlightInstance, Long> {
} 