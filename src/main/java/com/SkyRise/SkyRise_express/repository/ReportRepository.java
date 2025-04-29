package com.SkyRise.SkyRise_express.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SkyRise.SkyRise_express.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}