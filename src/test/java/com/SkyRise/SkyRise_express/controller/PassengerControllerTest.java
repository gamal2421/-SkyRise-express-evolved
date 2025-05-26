package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Passenger;
import com.SkyRise.SkyRise_express.service.PassengerService;
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

@WebMvcTest(PassengerController.class)
public class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @Test
    void createPassenger_shouldReturnCreatedPassenger() throws Exception {
        Passenger passenger = new Passenger();
        // Set properties for passenger

        when(passengerService.createPassenger(any(Passenger.class))).thenReturn(passenger);

        mockMvc.perform(post("/api/passengers")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual passenger JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getPassengerById_shouldReturnPassenger() throws Exception {
        Long passengerId = 1L;
        Passenger passenger = new Passenger();
        passenger.setId(passengerId);
        // Set other properties for passenger

        when(passengerService.getPassengerById(passengerId)).thenReturn(Optional.of(passenger));

        mockMvc.perform(get("/api/passengers/{id}", passengerId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(passengerId)); // Add more assertions
    }

    @Test
    void getAllPassengers_shouldReturnListOfPassengers() throws Exception {
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();
        // Set properties for passengers

        when(passengerService.getAllPassengers()).thenReturn(Arrays.asList(passenger1, passenger2));

        mockMvc.perform(get("/api/passengers"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updatePassenger_shouldReturnUpdatedPassenger() throws Exception {
        Long passengerId = 1L;
        Passenger passenger = new Passenger();
        passenger.setId(passengerId);
        // Set properties for passenger

        when(passengerService.updatePassenger(any(Passenger.class))).thenReturn(passenger);

        mockMvc.perform(put("/api/passengers/{id}", passengerId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated passenger JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(passengerId)); // Add more assertions
    }

    @Test
    void deletePassenger_shouldReturnNoContent() throws Exception {
        Long passengerId = 1L;

        mockMvc.perform(delete("/api/passengers/{id}", passengerId))
               .andExpect(status().isNoContent());
    }
} 