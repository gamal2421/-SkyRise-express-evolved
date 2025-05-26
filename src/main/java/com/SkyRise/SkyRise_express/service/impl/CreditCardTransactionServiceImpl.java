package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.CreditCardTransaction;
import com.SkyRise.SkyRise_express.repository.CreditCardTransactionRepository;
import com.SkyRise.SkyRise_express.service.CreditCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardTransactionServiceImpl implements CreditCardTransactionService {

    private final CreditCardTransactionRepository creditCardTransactionRepository;

    @Autowired
    public CreditCardTransactionServiceImpl(CreditCardTransactionRepository creditCardTransactionRepository) {
        this.creditCardTransactionRepository = creditCardTransactionRepository;
    }

    @Override
    public CreditCardTransaction createCreditCardTransaction(CreditCardTransaction creditCardTransaction) {
        return creditCardTransactionRepository.save(creditCardTransaction);
    }

    @Override
    public Optional<CreditCardTransaction> getCreditCardTransactionById(Long id) {
        return creditCardTransactionRepository.findById(id);
    }

    @Override
    public List<CreditCardTransaction> getAllCreditCardTransactions() {
        return creditCardTransactionRepository.findAll();
    }

    @Override
    public CreditCardTransaction updateCreditCardTransaction(CreditCardTransaction creditCardTransaction) {
        // You might want to add logic here to check if the creditCardTransaction exists before updating
        return creditCardTransactionRepository.save(creditCardTransaction);
    }

    @Override
    public void deleteCreditCardTransaction(Long id) {
        creditCardTransactionRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 