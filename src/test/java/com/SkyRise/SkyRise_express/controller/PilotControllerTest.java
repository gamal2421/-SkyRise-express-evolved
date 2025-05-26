package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Pilot;
import com.SkyRise.SkyRise_express.service.PilotService;
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

@WebMvcTest(PilotController.class)
public class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PilotService pilotService;

    @Test
    void createPilot_shouldReturnCreatedPilot() throws Exception {
        Pilot pilot = new Pilot();
        // Set properties for pilot

        when(pilotService.createPilot(any(Pilot.class))).thenReturn(pilot);

        mockMvc.perform(post("/api/pilots")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual pilot JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getPilotById_shouldReturnPilot() throws Exception {
        Long pilotId = 1L;
        Pilot pilot = new Pilot();
        pilot.setId(pilotId);
        // Set other properties for pilot

        when(pilotService.getPilotById(pilotId)).thenReturn(Optional.of(pilot));

        mockMvc.perform(get("/api/pilots/{id}", pilotId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(pilotId)); // Add more assertions
    }

    @Test
    void getAllPilots_shouldReturnListOfPilots() throws Exception {
        Pilot pilot1 = new Pilot();
        Pilot pilot2 = new Pilot();
        // Set properties for pilots

        when(pilotService.getAllPilots()).thenReturn(Arrays.asList(pilot1, pilot2));

        mockMvc.perform(get("/api/pilots"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updatePilot_shouldReturnUpdatedPilot() throws Exception {
        Long pilotId = 1L;
        Pilot pilot = new Pilot();
        pilot.setId(pilotId);
        // Set properties for pilot

        when(pilotService.updatePilot(any(Pilot.class))).thenReturn(pilot);

        mockMvc.perform(put("/api/pilots/{id}", pilotId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated pilot JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(pilotId)); // Add more assertions
    }

    @Test
    void deletePilot_shouldReturnNoContent() throws Exception {
        Long pilotId = 1L;

        mockMvc.perform(delete("/api/pilots/{id}", pilotId))
               .andExpect(status().isNoContent());
    }
} 