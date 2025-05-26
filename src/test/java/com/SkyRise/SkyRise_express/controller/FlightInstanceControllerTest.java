package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.service.FlightInstanceService;
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

@WebMvcTest(FlightInstanceController.class)
public class FlightInstanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightInstanceService flightInstanceService;

    @Test
    void createFlightInstance_shouldReturnCreatedFlightInstance() throws Exception {
        FlightInstance flightInstance = new FlightInstance();
        // Set properties for flightInstance

        when(flightInstanceService.createFlightInstance(any(FlightInstance.class))).thenReturn(flightInstance);

        mockMvc.perform(post("/api/flightinstances")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual flightInstance JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getFlightInstanceById_shouldReturnFlightInstance() throws Exception {
        Long instanceId = 1L;
        FlightInstance flightInstance = new FlightInstance();
        flightInstance.setId(instanceId);
        // Set other properties for flightInstance

        when(flightInstanceService.getFlightInstanceById(instanceId)).thenReturn(Optional.of(flightInstance));

        mockMvc.perform(get("/api/flightinstances/{id}", instanceId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(instanceId)); // Add more assertions
    }

    @Test
    void getAllFlightInstances_shouldReturnListOfFlightInstances() throws Exception {
        FlightInstance instance1 = new FlightInstance();
        FlightInstance instance2 = new FlightInstance();
        // Set properties for flightInstances

        when(flightInstanceService.getAllFlightInstances()).thenReturn(Arrays.asList(instance1, instance2));

        mockMvc.perform(get("/api/flightinstances"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateFlightInstance_shouldReturnUpdatedFlightInstance() throws Exception {
        Long instanceId = 1L;
        FlightInstance flightInstance = new FlightInstance();
        flightInstance.setId(instanceId);
        // Set properties for flightInstance

        when(flightInstanceService.updateFlightInstance(any(FlightInstance.class))).thenReturn(flightInstance);

        mockMvc.perform(put("/api/flightinstances/{id}", instanceId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated flightInstance JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(instanceId)); // Add more assertions
    }

    @Test
    void deleteFlightInstance_shouldReturnNoContent() throws Exception {
        Long instanceId = 1L;

        mockMvc.perform(delete("/api/flightinstances/{id}", instanceId))
               .andExpect(status().isNoContent());
    }
} 