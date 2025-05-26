package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Seat;
import com.SkyRise.SkyRise_express.service.SeatService;
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

@WebMvcTest(SeatController.class)
public class SeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    @Test
    void createSeat_shouldReturnCreatedSeat() throws Exception {
        Seat seat = new Seat();
        // Set properties for seat

        when(seatService.createSeat(any(Seat.class))).thenReturn(seat);

        mockMvc.perform(post("/api/seats")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual seat JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getSeatById_shouldReturnSeat() throws Exception {
        Long seatId = 1L;
        Seat seat = new Seat();
        seat.setId(seatId);
        // Set other properties for seat

        when(seatService.getSeatById(seatId)).thenReturn(Optional.of(seat));

        mockMvc.perform(get("/api/seats/{id}", seatId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(seatId)); // Add more assertions
    }

    @Test
    void getAllSeats_shouldReturnListOfSeats() throws Exception {
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        // Set properties for seats

        when(seatService.getAllSeats()).thenReturn(Arrays.asList(seat1, seat2));

        mockMvc.perform(get("/api/seats"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateSeat_shouldReturnUpdatedSeat() throws Exception {
        Long seatId = 1L;
        Seat seat = new Seat();
        seat.setId(seatId);
        // Set properties for seat

        when(seatService.updateSeat(any(Seat.class))).thenReturn(seat);

        mockMvc.perform(put("/api/seats/{id}", seatId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated seat JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(seatId)); // Add more assertions
    }

    @Test
    void deleteSeat_shouldReturnNoContent() throws Exception {
        Long seatId = 1L;

        mockMvc.perform(delete("/api/seats/{id}", seatId))
               .andExpect(status().isNoContent());
    }
} 