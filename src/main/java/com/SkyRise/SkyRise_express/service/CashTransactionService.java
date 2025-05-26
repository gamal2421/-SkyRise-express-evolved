package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.CashTransaction;

import java.util.List;
import java.util.Optional;

public interface CashTransactionService {

    CashTransaction createCashTransaction(CashTransaction cashTransaction);

    Optional<CashTransaction> getCashTransactionById(Long id);

    List<CashTransaction> getAllCashTransactions();

    CashTransaction updateCashTransaction(CashTransaction cashTransaction);

    void deleteCashTransaction(Long id);

    // Add other relevant methods based on your business logic
} 