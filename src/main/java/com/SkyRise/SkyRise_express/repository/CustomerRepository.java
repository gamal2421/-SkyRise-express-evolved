package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
} 