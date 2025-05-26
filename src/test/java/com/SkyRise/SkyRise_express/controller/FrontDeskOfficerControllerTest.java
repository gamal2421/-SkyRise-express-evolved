package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FrontDeskOfficer;
import com.SkyRise.SkyRise_express.service.FrontDeskOfficerService;
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

@WebMvcTest(FrontDeskOfficerController.class)
public class FrontDeskOfficerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FrontDeskOfficerService frontDeskOfficerService;

    @Test
    void createFrontDeskOfficer_shouldReturnCreatedFrontDeskOfficer() throws Exception {
        FrontDeskOfficer frontDeskOfficer = new FrontDeskOfficer();
        // Set properties for frontDeskOfficer

        when(frontDeskOfficerService.createFrontDeskOfficer(any(FrontDeskOfficer.class))).thenReturn(frontDeskOfficer);

        mockMvc.perform(post("/api/frontdeskofficers")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual frontDeskOfficer JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getFrontDeskOfficerById_shouldReturnFrontDeskOfficer() throws Exception {
        Long officerId = 1L;
        FrontDeskOfficer frontDeskOfficer = new FrontDeskOfficer();
        frontDeskOfficer.setId(officerId);
        // Set other properties for frontDeskOfficer

        when(frontDeskOfficerService.getFrontDeskOfficerById(officerId)).thenReturn(Optional.of(frontDeskOfficer));

        mockMvc.perform(get("/api/frontdeskofficers/{id}", officerId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(officerId)); // Add more assertions
    }

    @Test
    void getAllFrontDeskOfficers_shouldReturnListOfFrontDeskOfficers() throws Exception {
        FrontDeskOfficer officer1 = new FrontDeskOfficer();
        FrontDeskOfficer officer2 = new FrontDeskOfficer();
        // Set properties for frontDeskOfficers

        when(frontDeskOfficerService.getAllFrontDeskOfficers()).thenReturn(Arrays.asList(officer1, officer2));

        mockMvc.perform(get("/api/frontdeskofficers"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateFrontDeskOfficer_shouldReturnUpdatedFrontDeskOfficer() throws Exception {
        Long officerId = 1L;
        FrontDeskOfficer frontDeskOfficer = new FrontDeskOfficer();
        frontDeskOfficer.setId(officerId);
        // Set properties for frontDeskOfficer

        when(frontDeskOfficerService.updateFrontDeskOfficer(any(FrontDeskOfficer.class))).thenReturn(frontDeskOfficer);

        mockMvc.perform(put("/api/frontdeskofficers/{id}", officerId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated frontDeskOfficer JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(officerId)); // Add more assertions
    }

    @Test
    void deleteFrontDeskOfficer_shouldReturnNoContent() throws Exception {
        Long officerId = 1L;

        mockMvc.perform(delete("/api/frontdeskofficers/{id}", officerId))
               .andExpect(status().isNoContent());
    }
} 