package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.service.FlightService;
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

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    void createFlight_shouldReturnCreatedFlight() throws Exception {
        Flight flight = new Flight();
        // Set properties for flight

        when(flightService.createFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual flight JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getFlightById_shouldReturnFlight() throws Exception {
        Long flightId = 1L;
        Flight flight = new Flight();
        flight.setId(flightId);
        // Set other properties for flight

        when(flightService.getFlightById(flightId)).thenReturn(Optional.of(flight));

        mockMvc.perform(get("/api/flights/{id}", flightId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(flightId)); // Add more assertions
    }

    @Test
    void getAllFlights_shouldReturnListOfFlights() throws Exception {
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        // Set properties for flights

        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flight1, flight2));

        mockMvc.perform(get("/api/flights"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateFlight_shouldReturnUpdatedFlight() throws Exception {
        Long flightId = 1L;
        Flight flight = new Flight();
        flight.setId(flightId);
        // Set properties for flight

        when(flightService.updateFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(put("/api/flights/{id}", flightId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated flight JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(flightId)); // Add more assertions
    }

    @Test
    void deleteFlight_shouldReturnNoContent() throws Exception {
        Long flightId = 1L;

        mockMvc.perform(delete("/api/flights/{id}", flightId))
               .andExpect(status().isNoContent());
    }
} 