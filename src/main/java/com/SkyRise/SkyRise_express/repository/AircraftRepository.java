package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
} 