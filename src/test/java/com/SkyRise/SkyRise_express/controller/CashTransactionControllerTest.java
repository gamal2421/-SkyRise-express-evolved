package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CashTransaction;
import com.SkyRise.SkyRise_express.service.CashTransactionService;
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

@WebMvcTest(CashTransactionController.class)
public class CashTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CashTransactionService cashTransactionService;

    @Test
    void createCashTransaction_shouldReturnCreatedTransaction() throws Exception {
        CashTransaction transaction = new CashTransaction();
        // Set properties for transaction

        when(cashTransactionService.createCashTransaction(any(CashTransaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/api/cashtransactions")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual transaction JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getCashTransactionById_shouldReturnTransaction() throws Exception {
        Long transactionId = 1L;
        CashTransaction transaction = new CashTransaction();
        transaction.setId(transactionId);
        // Set other properties for transaction

        when(cashTransactionService.getCashTransactionById(transactionId)).thenReturn(Optional.of(transaction));

        mockMvc.perform(get("/api/cashtransactions/{id}", transactionId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(transactionId)); // Add more assertions
    }

    @Test
    void getAllCashTransactions_shouldReturnListOfTransactions() throws Exception {
        CashTransaction transaction1 = new CashTransaction();
        CashTransaction transaction2 = new CashTransaction();
        // Set properties for transactions

        when(cashTransactionService.getAllCashTransactions()).thenReturn(Arrays.asList(transaction1, transaction2));

        mockMvc.perform(get("/api/cashtransactions"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateCashTransaction_shouldReturnUpdatedTransaction() throws Exception {
        Long transactionId = 1L;
        CashTransaction transaction = new CashTransaction();
        transaction.setId(transactionId);
        // Set properties for transaction

        when(cashTransactionService.updateCashTransaction(any(CashTransaction.class))).thenReturn(transaction);

        mockMvc.perform(put("/api/cashtransactions/{id}", transactionId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated transaction JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(transactionId)); // Add more assertions
    }

    @Test
    void deleteCashTransaction_shouldReturnNoContent() throws Exception {
        Long transactionId = 1L;

        mockMvc.perform(delete("/api/cashtransactions/{id}", transactionId))
               .andExpect(status().isNoContent());
    }
} 