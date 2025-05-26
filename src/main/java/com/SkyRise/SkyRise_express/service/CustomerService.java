package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    // Add other relevant methods based on your business logic
} 