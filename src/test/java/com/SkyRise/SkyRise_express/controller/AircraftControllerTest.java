package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Aircraft;
import com.SkyRise.SkyRise_express.service.AircraftService;
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

@WebMvcTest(AircraftController.class)
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Test
    void createAircraft_shouldReturnCreatedAircraft() throws Exception {
        Aircraft aircraft = new Aircraft();
        // Set properties for aircraft

        when(aircraftService.createAircraft(any(Aircraft.class))).thenReturn(aircraft);

        mockMvc.perform(post("/api/aircraft")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual aircraft JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getAircraftById_shouldReturnAircraft() throws Exception {
        Long aircraftId = 1L;
        Aircraft aircraft = new Aircraft();
        aircraft.setId(aircraftId);
        // Set other properties for aircraft

        when(aircraftService.getAircraftById(aircraftId)).thenReturn(Optional.of(aircraft));

        mockMvc.perform(get("/api/aircraft/{id}", aircraftId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(aircraftId)); // Add more assertions
    }

    @Test
    void getAllAircraft_shouldReturnListOfAircraft() throws Exception {
        Aircraft aircraft1 = new Aircraft();
        Aircraft aircraft2 = new Aircraft();
        // Set properties for aircraft

        when(aircraftService.getAllAircraft()).thenReturn(Arrays.asList(aircraft1, aircraft2));

        mockMvc.perform(get("/api/aircraft"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateAircraft_shouldReturnUpdatedAircraft() throws Exception {
        Long aircraftId = 1L;
        Aircraft aircraft = new Aircraft();
        aircraft.setId(aircraftId);
        // Set properties for aircraft

        when(aircraftService.updateAircraft(any(Aircraft.class))).thenReturn(aircraft);

        mockMvc.perform(put("/api/aircraft/{id}", aircraftId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated aircraft JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(aircraftId)); // Add more assertions
    }

    @Test
    void deleteAircraft_shouldReturnNoContent() throws Exception {
        Long aircraftId = 1L;

        mockMvc.perform(delete("/api/aircraft/{id}", aircraftId))
               .andExpect(status().isNoContent());
    }
} 