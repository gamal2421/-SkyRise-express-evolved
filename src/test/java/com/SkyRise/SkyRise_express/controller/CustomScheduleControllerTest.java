package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.CustomSchedule;
import com.SkyRise.SkyRise_express.service.CustomScheduleService;
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

@WebMvcTest(CustomScheduleController.class)
public class CustomScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomScheduleService customScheduleService;

    @Test
    void createCustomSchedule_shouldReturnCreatedCustomSchedule() throws Exception {
        CustomSchedule customSchedule = new CustomSchedule();
        // Set properties for customSchedule

        when(customScheduleService.createCustomSchedule(any(CustomSchedule.class))).thenReturn(customSchedule);

        mockMvc.perform(post("/api/customschedules")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual customSchedule JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getCustomScheduleById_shouldReturnCustomSchedule() throws Exception {
        Long scheduleId = 1L;
        CustomSchedule customSchedule = new CustomSchedule();
        customSchedule.setId(scheduleId);
        // Set other properties for customSchedule

        when(customScheduleService.getCustomScheduleById(scheduleId)).thenReturn(Optional.of(customSchedule));

        mockMvc.perform(get("/api/customschedules/{id}", scheduleId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(scheduleId)); // Add more assertions
    }

    @Test
    void getAllCustomSchedules_shouldReturnListOfCustomSchedules() throws Exception {
        CustomSchedule schedule1 = new CustomSchedule();
        CustomSchedule schedule2 = new CustomSchedule();
        // Set properties for customSchedules

        when(customScheduleService.getAllCustomSchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(get("/api/customschedules"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateCustomSchedule_shouldReturnUpdatedCustomSchedule() throws Exception {
        Long scheduleId = 1L;
        CustomSchedule customSchedule = new CustomSchedule();
        customSchedule.setId(scheduleId);
        // Set properties for customSchedule

        when(customScheduleService.updateCustomSchedule(any(CustomSchedule.class))).thenReturn(customSchedule);

        mockMvc.perform(put("/api/customschedules/{id}", scheduleId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated customSchedule JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(scheduleId)); // Add more assertions
    }

    @Test
    void deleteCustomSchedule_shouldReturnNoContent() throws Exception {
        Long scheduleId = 1L;

        mockMvc.perform(delete("/api/customschedules/{id}", scheduleId))
               .andExpect(status().isNoContent());
    }
} 