package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Airline;
import com.SkyRise.SkyRise_express.service.AirlineService;
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

@WebMvcTest(AirlineController.class)
public class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Test
    void createAirline_shouldReturnCreatedAirline() throws Exception {
        Airline airline = new Airline();
        // Set properties for airline

        when(airlineService.createAirline(any(Airline.class))).thenReturn(airline);

        mockMvc.perform(post("/api/airlines")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual airline JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getAirlineById_shouldReturnAirline() throws Exception {
        Long airlineId = 1L;
        Airline airline = new Airline();
        airline.setId(airlineId);
        // Set other properties for airline

        when(airlineService.getAirlineById(airlineId)).thenReturn(Optional.of(airline));

        mockMvc.perform(get("/api/airlines/{id}", airlineId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(airlineId)); // Add more assertions
    }

    @Test
    void getAllAirlines_shouldReturnListOfAirlines() throws Exception {
        Airline airline1 = new Airline();
        Airline airline2 = new Airline();
        // Set properties for airlines

        when(airlineService.getAllAirlines()).thenReturn(Arrays.asList(airline1, airline2));

        mockMvc.perform(get("/api/airlines"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateAirline_shouldReturnUpdatedAirline() throws Exception {
        Long airlineId = 1L;
        Airline airline = new Airline();
        airline.setId(airlineId);
        // Set properties for airline

        when(airlineService.updateAirline(any(Airline.class))).thenReturn(airline);

        mockMvc.perform(put("/api/airlines/{id}", airlineId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated airline JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(airlineId)); // Add more assertions
    }

    @Test
    void deleteAirline_shouldReturnNoContent() throws Exception {
        Long airlineId = 1L;

        mockMvc.perform(delete("/api/airlines/{id}", airlineId))
               .andExpect(status().isNoContent());
    }
} 