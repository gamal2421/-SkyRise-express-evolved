package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CreditCardTransaction;
import com.SkyRise.SkyRise_express.service.CreditCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditcardtransactions")
public class CreditCardTransactionController {

    private final CreditCardTransactionService creditCardTransactionService;

    @Autowired
    public CreditCardTransactionController(CreditCardTransactionService creditCardTransactionService) {
        this.creditCardTransactionService = creditCardTransactionService;
    }

    @PostMapping
    public ResponseEntity<CreditCardTransaction> createCreditCardTransaction(@RequestBody CreditCardTransaction creditCardTransaction) {
        CreditCardTransaction createdCreditCardTransaction = creditCardTransactionService.createCreditCardTransaction(creditCardTransaction);
        return new ResponseEntity<>(createdCreditCardTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardTransaction> getCreditCardTransactionById(@PathVariable Long id) {
        Optional<CreditCardTransaction> creditCardTransaction = creditCardTransactionService.getCreditCardTransactionById(id);
        return creditCardTransaction.map(ResponseEntity::ok)
                                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CreditCardTransaction>> getAllCreditCardTransactions() {
        List<CreditCardTransaction> creditCardTransactions = creditCardTransactionService.getAllCreditCardTransactions();
        return new ResponseEntity<>(creditCardTransactions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardTransaction> updateCreditCardTransaction(@PathVariable Long id, @RequestBody CreditCardTransaction creditCardTransaction) {
        // You might want to add logic here to check if the creditCardTransaction with id exists before updating
        creditCardTransaction.setId(id); // Ensure the correct ID is set for update
        CreditCardTransaction updatedCreditCardTransaction = creditCardTransactionService.updateCreditCardTransaction(creditCardTransaction);
        return ResponseEntity.ok(updatedCreditCardTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCardTransaction(@PathVariable Long id) {
        creditCardTransactionService.deleteCreditCardTransaction(id);
        return ResponseEntity.noContent().build();
    }
} 