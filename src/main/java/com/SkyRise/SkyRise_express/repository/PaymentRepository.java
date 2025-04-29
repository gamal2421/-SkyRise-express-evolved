package com.SkyRise.SkyRise_express.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SkyRise.SkyRise_express.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}