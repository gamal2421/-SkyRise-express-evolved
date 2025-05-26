package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
} 