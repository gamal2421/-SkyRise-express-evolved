package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account createAccount(Account account);

    Optional<Account> getAccountById(Long id);

    List<Account> getAllAccounts();

    Account updateAccount(Account account);

    void deleteAccount(Long id);

    // Add other relevant methods based on your business logic
} 