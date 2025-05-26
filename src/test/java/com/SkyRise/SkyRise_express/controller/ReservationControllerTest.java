package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Reservation;
import com.SkyRise.SkyRise_express.service.ReservationService;
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

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    void createReservation_shouldReturnCreatedReservation() throws Exception {
        Reservation reservation = new Reservation();
        // Set properties for reservation

        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/api/reservations")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual reservation JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.reservationNumber").exists()); // Add more assertions, note the primary key name
    }

    @Test
    void getReservationById_shouldReturnReservation() throws Exception {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationId);
        // Set other properties for reservation

        when(reservationService.getReservationById(reservationId)).thenReturn(Optional.of(reservation));

        mockMvc.perform(get("/api/reservations/{id}", reservationId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.reservationNumber").value(reservationId)); // Add more assertions
    }

    @Test
    void getAllReservations_shouldReturnListOfReservations() throws Exception {
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        // Set properties for reservations

        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(reservation1, reservation2));

        mockMvc.perform(get("/api/reservations"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateReservation_shouldReturnUpdatedReservation() throws Exception {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationId);
        // Set properties for reservation

        when(reservationService.updateReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(put("/api/reservations/{id}", reservationId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated reservation JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.reservationNumber").value(reservationId)); // Add more assertions
    }

    @Test
    void deleteReservation_shouldReturnNoContent() throws Exception {
        Long reservationId = 1L;

        mockMvc.perform(delete("/api/reservations/{id}", reservationId))
               .andExpect(status().isNoContent());
    }
} 