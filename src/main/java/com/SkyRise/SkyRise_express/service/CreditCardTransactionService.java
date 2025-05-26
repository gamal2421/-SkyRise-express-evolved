package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.CreditCardTransaction;

import java.util.List;
import java.util.Optional;

public interface CreditCardTransactionService {

    CreditCardTransaction createCreditCardTransaction(CreditCardTransaction creditCardTransaction);

    Optional<CreditCardTransaction> getCreditCardTransactionById(Long id);

    List<CreditCardTransaction> getAllCreditCardTransactions();

    CreditCardTransaction updateCreditCardTransaction(CreditCardTransaction creditCardTransaction);

    void deleteCreditCardTransaction(Long id);

    // Add other relevant methods based on your business logic
} 