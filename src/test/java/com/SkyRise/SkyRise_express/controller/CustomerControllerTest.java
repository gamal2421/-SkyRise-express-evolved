package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Customer;
import com.SkyRise.SkyRise_express.service.CustomerService;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomer_shouldReturnCreatedCustomer() throws Exception {
        Customer customer = new Customer();
        // Set properties for customer

        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual customer JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getCustomerById_shouldReturnCustomer() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        // Set other properties for customer

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/{id}", customerId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(customerId)); // Add more assertions
    }

    @Test
    void getAllCustomers_shouldReturnListOfCustomers() throws Exception {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        // Set properties for customers

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/customers"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateCustomer_shouldReturnUpdatedCustomer() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        // Set properties for customer

        when(customerService.updateCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/api/customers/{id}", customerId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated customer JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(customerId)); // Add more assertions
    }

    @Test
    void deleteCustomer_shouldReturnNoContent() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(delete("/api/customers/{id}", customerId))
               .andExpect(status().isNoContent());
    }
} 