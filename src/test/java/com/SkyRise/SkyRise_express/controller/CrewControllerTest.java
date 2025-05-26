package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Crew;
import com.SkyRise.SkyRise_express.service.CrewService;
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

@WebMvcTest(CrewController.class)
public class CrewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrewService crewService;

    @Test
    void createCrew_shouldReturnCreatedCrew() throws Exception {
        Crew crew = new Crew();
        // Set properties for crew

        when(crewService.createCrew(any(Crew.class))).thenReturn(crew);

        mockMvc.perform(post("/api/crews")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual crew JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getCrewById_shouldReturnCrew() throws Exception {
        Long crewId = 1L;
        Crew crew = new Crew();
        crew.setId(crewId);
        // Set other properties for crew

        when(crewService.getCrewById(crewId)).thenReturn(Optional.of(crew));

        mockMvc.perform(get("/api/crews/{id}", crewId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(crewId)); // Add more assertions
    }

    @Test
    void getAllCrews_shouldReturnListOfCrews() throws Exception {
        Crew crew1 = new Crew();
        Crew crew2 = new Crew();
        // Set properties for crews

        when(crewService.getAllCrews()).thenReturn(Arrays.asList(crew1, crew2));

        mockMvc.perform(get("/api/crews"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateCrew_shouldReturnUpdatedCrew() throws Exception {
        Long crewId = 1L;
        Crew crew = new Crew();
        crew.setId(crewId);
        // Set properties for crew

        when(crewService.updateCrew(any(Crew.class))).thenReturn(crew);

        mockMvc.perform(put("/api/crews/{id}", crewId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated crew JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(crewId)); // Add more assertions
    }

    @Test
    void deleteCrew_shouldReturnNoContent() throws Exception {
        Long crewId = 1L;

        mockMvc.perform(delete("/api/crews/{id}", crewId))
               .andExpect(status().isNoContent());
    }
} 