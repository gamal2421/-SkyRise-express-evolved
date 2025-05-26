package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.WeeklySchedule;
import com.SkyRise.SkyRise_express.service.WeeklyScheduleService;
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

@WebMvcTest(WeeklyScheduleController.class)
public class WeeklyScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeeklyScheduleService weeklyScheduleService;

    @Test
    void createWeeklySchedule_shouldReturnCreatedWeeklySchedule() throws Exception {
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        // Set properties for weeklySchedule

        when(weeklyScheduleService.createWeeklySchedule(any(WeeklySchedule.class))).thenReturn(weeklySchedule);

        mockMvc.perform(post("/api/weeklyschedules")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual weeklySchedule JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getWeeklyScheduleById_shouldReturnWeeklySchedule() throws Exception {
        Long scheduleId = 1L;
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        weeklySchedule.setId(scheduleId);
        // Set other properties for weeklySchedule

        when(weeklyScheduleService.getWeeklyScheduleById(scheduleId)).thenReturn(Optional.of(weeklySchedule));

        mockMvc.perform(get("/api/weeklyschedules/{id}", scheduleId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(scheduleId)); // Add more assertions
    }

    @Test
    void getAllWeeklySchedules_shouldReturnListOfWeeklySchedules() throws Exception {
        WeeklySchedule schedule1 = new WeeklySchedule();
        WeeklySchedule schedule2 = new WeeklySchedule();
        // Set properties for weeklySchedules

        when(weeklyScheduleService.getAllWeeklySchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(get("/api/weeklyschedules"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateWeeklySchedule_shouldReturnUpdatedWeeklySchedule() throws Exception {
        Long scheduleId = 1L;
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        weeklySchedule.setId(scheduleId);
        // Set properties for weeklySchedule

        when(weeklyScheduleService.updateWeeklySchedule(any(WeeklySchedule.class))).thenReturn(weeklySchedule);

        mockMvc.perform(put("/api/weeklyschedules/{id}", scheduleId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated weeklySchedule JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(scheduleId)); // Add more assertions
    }

    @Test
    void deleteWeeklySchedule_shouldReturnNoContent() throws Exception {
        Long scheduleId = 1L;

        mockMvc.perform(delete("/api/weeklyschedules/{id}", scheduleId))
               .andExpect(status().isNoContent());
    }
} 