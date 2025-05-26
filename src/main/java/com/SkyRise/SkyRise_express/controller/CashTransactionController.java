package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CashTransaction;
import com.SkyRise.SkyRise_express.service.CashTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cashtransactions")
public class CashTransactionController {

    private final CashTransactionService cashTransactionService;

    @Autowired
    public CashTransactionController(CashTransactionService cashTransactionService) {
        this.cashTransactionService = cashTransactionService;
    }

    @PostMapping
    public ResponseEntity<CashTransaction> createCashTransaction(@RequestBody CashTransaction cashTransaction) {
        CashTransaction createdCashTransaction = cashTransactionService.createCashTransaction(cashTransaction);
        return new ResponseEntity<>(createdCashTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashTransaction> getCashTransactionById(@PathVariable Long id) {
        Optional<CashTransaction> cashTransaction = cashTransactionService.getCashTransactionById(id);
        return cashTransaction.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CashTransaction>> getAllCashTransactions() {
        List<CashTransaction> cashTransactions = cashTransactionService.getAllCashTransactions();
        return new ResponseEntity<>(cashTransactions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashTransaction> updateCashTransaction(@PathVariable Long id, @RequestBody CashTransaction cashTransaction) {
        // You might want to add logic here to check if the cashTransaction with id exists before updating
        cashTransaction.setId(id); // Ensure the correct ID is set for update
        CashTransaction updatedCashTransaction = cashTransactionService.updateCashTransaction(cashTransaction);
        return ResponseEntity.ok(updatedCashTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCashTransaction(@PathVariable Long id) {
        cashTransactionService.deleteCashTransaction(id);
        return ResponseEntity.noContent().build();
    }
} 