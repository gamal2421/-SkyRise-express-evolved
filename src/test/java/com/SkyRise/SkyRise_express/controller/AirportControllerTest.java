package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Airport;
import com.SkyRise.SkyRise_express.service.AirportService;
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

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void createAirport_shouldReturnCreatedAirport() throws Exception {
        Airport airport = new Airport();
        // Set properties for airport

        when(airportService.createAirport(any(Airport.class))).thenReturn(airport);

        mockMvc.perform(post("/api/airports")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual airport JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getAirportById_shouldReturnAirport() throws Exception {
        Long airportId = 1L;
        Airport airport = new Airport();
        airport.setId(airportId);
        // Set other properties for airport

        when(airportService.getAirportById(airportId)).thenReturn(Optional.of(airport));

        mockMvc.perform(get("/api/airports/{id}", airportId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(airportId)); // Add more assertions
    }

    @Test
    void getAllAirports_shouldReturnListOfAirports() throws Exception {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();
        // Set properties for airports

        when(airportService.getAllAirports()).thenReturn(Arrays.asList(airport1, airport2));

        mockMvc.perform(get("/api/airports"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateAirport_shouldReturnUpdatedAirport() throws Exception {
        Long airportId = 1L;
        Airport airport = new Airport();
        airport.setId(airportId);
        // Set properties for airport

        when(airportService.updateAirport(any(Airport.class))).thenReturn(airport);

        mockMvc.perform(put("/api/airports/{id}", airportId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated airport JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(airportId)); // Add more assertions
    }

    @Test
    void deleteAirport_shouldReturnNoContent() throws Exception {
        Long airportId = 1L;

        mockMvc.perform(delete("/api/airports/{id}", airportId))
               .andExpect(status().isNoContent());
    }
} 