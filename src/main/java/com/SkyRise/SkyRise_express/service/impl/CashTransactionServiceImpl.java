package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.CashTransaction;
import com.SkyRise.SkyRise_express.repository.CashTransactionRepository;
import com.SkyRise.SkyRise_express.service.CashTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashTransactionServiceImpl implements CashTransactionService {

    private final CashTransactionRepository cashTransactionRepository;

    @Autowired
    public CashTransactionServiceImpl(CashTransactionRepository cashTransactionRepository) {
        this.cashTransactionRepository = cashTransactionRepository;
    }

    @Override
    public CashTransaction createCashTransaction(CashTransaction cashTransaction) {
        return cashTransactionRepository.save(cashTransaction);
    }

    @Override
    public Optional<CashTransaction> getCashTransactionById(Long id) {
        return cashTransactionRepository.findById(id);
    }

    @Override
    public List<CashTransaction> getAllCashTransactions() {
        return cashTransactionRepository.findAll();
    }

    @Override
    public CashTransaction updateCashTransaction(CashTransaction cashTransaction) {
        // You might want to add logic here to check if the cashTransaction exists before updating
        return cashTransactionRepository.save(cashTransaction);
    }

    @Override
    public void deleteCashTransaction(Long id) {
        cashTransactionRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 