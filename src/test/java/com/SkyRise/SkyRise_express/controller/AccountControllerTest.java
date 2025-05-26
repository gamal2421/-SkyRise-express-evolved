package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Account;
import com.SkyRise.SkyRise_express.service.AccountService;
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

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void createAccount_shouldReturnCreatedAccount() throws Exception {
        Account account = new Account();
        // Set properties for account

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/api/accounts")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual account JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getAccountById_shouldReturnAccount() throws Exception {
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        // Set other properties for account

        when(accountService.getAccountById(accountId)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/api/accounts/{id}", accountId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(accountId)); // Add more assertions
    }

    @Test
    void getAllAccounts_shouldReturnListOfAccounts() throws Exception {
        Account account1 = new Account();
        Account account2 = new Account();
        // Set properties for accounts

        when(accountService.getAllAccounts()).thenReturn(Arrays.asList(account1, account2));

        mockMvc.perform(get("/api/accounts"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateAccount_shouldReturnUpdatedAccount() throws Exception {
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        // Set properties for account

        when(accountService.updateAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(put("/api/accounts/{id}", accountId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated account JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(accountId)); // Add more assertions
    }

    @Test
    void deleteAccount_shouldReturnNoContent() throws Exception {
        Long accountId = 1L;

        mockMvc.perform(delete("/api/accounts/{id}", accountId))
               .andExpect(status().isNoContent());
    }
} 