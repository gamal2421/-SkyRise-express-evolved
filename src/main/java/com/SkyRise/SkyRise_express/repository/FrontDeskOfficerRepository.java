package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.FrontDeskOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontDeskOfficerRepository extends JpaRepository<FrontDeskOfficer, Long> {
} 