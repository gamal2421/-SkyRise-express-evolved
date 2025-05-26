package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {
} 