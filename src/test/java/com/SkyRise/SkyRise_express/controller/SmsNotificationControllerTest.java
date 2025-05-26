package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.SmsNotification;
import com.SkyRise.SkyRise_express.service.SmsNotificationService;
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

@WebMvcTest(SmsNotificationController.class)
public class SmsNotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SmsNotificationService smsNotificationService;

    @Test
    void createSmsNotification_shouldReturnCreatedNotification() throws Exception {
        SmsNotification notification = new SmsNotification();
        // Set properties for notification

        when(smsNotificationService.createSmsNotification(any(SmsNotification.class))).thenReturn(notification);

        mockMvc.perform(post("/api/smsnotifications")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual notification JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.notificationId").exists()); // Add more assertions
    }

    @Test
    void getSmsNotificationById_shouldReturnNotification() throws Exception {
        Long notificationId = 1L;
        SmsNotification notification = new SmsNotification();
        notification.setNotificationId(notificationId);
        // Set other properties for notification

        when(smsNotificationService.getSmsNotificationById(notificationId)).thenReturn(Optional.of(notification));

        mockMvc.perform(get("/api/smsnotifications/{id}", notificationId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void getAllSmsNotifications_shouldReturnListOfNotifications() throws Exception {
        SmsNotification notification1 = new SmsNotification();
        SmsNotification notification2 = new SmsNotification();
        // Set properties for notifications

        when(smsNotificationService.getAllSmsNotifications()).thenReturn(Arrays.asList(notification1, notification2));

        mockMvc.perform(get("/api/smsnotifications"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateSmsNotification_shouldReturnUpdatedNotification() throws Exception {
        Long notificationId = 1L;
        SmsNotification notification = new SmsNotification();
        notification.setNotificationId(notificationId);
        // Set properties for notification

        when(smsNotificationService.updateSmsNotification(any(SmsNotification.class))).thenReturn(notification);

        mockMvc.perform(put("/api/smsnotifications/{id}", notificationId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated notification JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void deleteSmsNotification_shouldReturnNoContent() throws Exception {
        Long notificationId = 1L;

        mockMvc.perform(delete("/api/smsnotifications/{id}", notificationId))
               .andExpect(status().isNoContent());
    }
} 