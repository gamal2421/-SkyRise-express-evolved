package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightSeat;
import com.SkyRise.SkyRise_express.service.FlightSeatService;
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

@WebMvcTest(FlightSeatController.class)
public class FlightSeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightSeatService flightSeatService;

    @Test
    void createFlightSeat_shouldReturnCreatedFlightSeat() throws Exception {
        FlightSeat flightSeat = new FlightSeat();
        // Set properties for flightSeat

        when(flightSeatService.createFlightSeat(any(FlightSeat.class))).thenReturn(flightSeat);

        mockMvc.perform(post("/api/flightseats")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual flightSeat JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getFlightSeatById_shouldReturnFlightSeat() throws Exception {
        Long flightSeatId = 1L;
        FlightSeat flightSeat = new FlightSeat();
        flightSeat.setId(flightSeatId);
        // Set other properties for flightSeat

        when(flightSeatService.getFlightSeatById(flightSeatId)).thenReturn(Optional.of(flightSeat));

        mockMvc.perform(get("/api/flightseats/{id}", flightSeatId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(flightSeatId)); // Add more assertions
    }

    @Test
    void getAllFlightSeats_shouldReturnListOfFlightSeats() throws Exception {
        FlightSeat flightSeat1 = new FlightSeat();
        FlightSeat flightSeat2 = new FlightSeat();
        // Set properties for flightSeats

        when(flightSeatService.getAllFlightSeats()).thenReturn(Arrays.asList(flightSeat1, flightSeat2));

        mockMvc.perform(get("/api/flightseats"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateFlightSeat_shouldReturnUpdatedFlightSeat() throws Exception {
        Long flightSeatId = 1L;
        FlightSeat flightSeat = new FlightSeat();
        flightSeat.setId(flightSeatId);
        // Set properties for flightSeat

        when(flightSeatService.updateFlightSeat(any(FlightSeat.class))).thenReturn(flightSeat);

        mockMvc.perform(put("/api/flightseats/{id}", flightSeatId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated flightSeat JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(flightSeatId)); // Add more assertions
    }

    @Test
    void deleteFlightSeat_shouldReturnNoContent() throws Exception {
        Long flightSeatId = 1L;

        mockMvc.perform(delete("/api/flightseats/{id}", flightSeatId))
               .andExpect(status().isNoContent());
    }
} 