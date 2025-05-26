package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Itinerary;
import com.SkyRise.SkyRise_express.service.ItineraryService;
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

@WebMvcTest(ItineraryController.class)
public class ItineraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItineraryService itineraryService;

    @Test
    void createItinerary_shouldReturnCreatedItinerary() throws Exception {
        Itinerary itinerary = new Itinerary();
        // Set properties for itinerary

        when(itineraryService.createItinerary(any(Itinerary.class))).thenReturn(itinerary);

        mockMvc.perform(post("/api/itineraries")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual itinerary JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getItineraryById_shouldReturnItinerary() throws Exception {
        Long itineraryId = 1L;
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);
        // Set other properties for itinerary

        when(itineraryService.getItineraryById(itineraryId)).thenReturn(Optional.of(itinerary));

        mockMvc.perform(get("/api/itineraries/{id}", itineraryId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(itineraryId)); // Add more assertions
    }

    @Test
    void getAllItineraries_shouldReturnListOfItineraries() throws Exception {
        Itinerary itinerary1 = new Itinerary();
        Itinerary itinerary2 = new Itinerary();
        // Set properties for itineraries

        when(itineraryService.getAllItineraries()).thenReturn(Arrays.asList(itinerary1, itinerary2));

        mockMvc.perform(get("/api/itineraries"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateItinerary_shouldReturnUpdatedItinerary() throws Exception {
        Long itineraryId = 1L;
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);
        // Set properties for itinerary

        when(itineraryService.updateItinerary(any(Itinerary.class))).thenReturn(itinerary);

        mockMvc.perform(put("/api/itineraries/{id}", itineraryId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated itinerary JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(itineraryId)); // Add more assertions
    }

    @Test
    void deleteItinerary_shouldReturnNoContent() throws Exception {
        Long itineraryId = 1L;

        mockMvc.perform(delete("/api/itineraries/{id}", itineraryId))
               .andExpect(status().isNoContent());
    }
} 