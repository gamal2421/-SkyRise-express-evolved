package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
} 