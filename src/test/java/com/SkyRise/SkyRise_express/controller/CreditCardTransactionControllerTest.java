package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CreditCardTransaction;
import com.SkyRise.SkyRise_express.service.CreditCardTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditCardTransactionController.class)
public class CreditCardTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardTransactionService creditCardTransactionService;

    @Test
    void createCreditCardTransaction_shouldReturnCreatedTransaction() throws Exception {
        CreditCardTransaction transaction = new CreditCardTransaction();
        // Set properties for transaction

        when(creditCardTransactionService.createCreditCardTransaction(any(CreditCardTransaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/api/creditcardtransactions")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual transaction JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getCreditCardTransactionById_shouldReturnTransaction() throws Exception {
        Long transactionId = 1L;
        CreditCardTransaction transaction = new CreditCardTransaction();
        transaction.setId(transactionId);
        // Set other properties for transaction

        when(creditCardTransactionService.getCreditCardTransactionById(transactionId)).thenReturn(Optional.of(transaction));

        mockMvc.perform(get("/api/creditcardtransactions/{id}", transactionId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(transactionId)); // Add more assertions
    }

    @Test
    void getAllCreditCardTransactions_shouldReturnListOfTransactions() throws Exception {
        CreditCardTransaction transaction1 = new CreditCardTransaction();
        CreditCardTransaction transaction2 = new CreditCardTransaction();
        // Set properties for transactions

        when(creditCardTransactionService.getAllCreditCardTransactions()).thenReturn(Arrays.asList(transaction1, transaction2));

        mockMvc.perform(get("/api/creditcardtransactions"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateCreditCardTransaction_shouldReturnUpdatedTransaction() throws Exception {
        Long transactionId = 1L;
        CreditCardTransaction transaction = new CreditCardTransaction();
        transaction.setId(transactionId);
        // Set properties for transaction

        when(creditCardTransactionService.updateCreditCardTransaction(any(CreditCardTransaction.class))).thenReturn(transaction);

        mockMvc.perform(put("/api/creditcardtransactions/{id}", transactionId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated transaction JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(transactionId)); // Add more assertions
    }

    @Test
    void deleteCreditCardTransaction_shouldReturnNoContent() throws Exception {
        Long transactionId = 1L;

        mockMvc.perform(delete("/api/creditcardtransactions/{id}", transactionId))
               .andExpect(status().isNoContent());
    }
} 