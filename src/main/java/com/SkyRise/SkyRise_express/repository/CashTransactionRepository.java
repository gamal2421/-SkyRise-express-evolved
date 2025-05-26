package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.CashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashTransactionRepository extends JpaRepository<CashTransaction, Long> {
} 