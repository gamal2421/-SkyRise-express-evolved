package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.EmailNotification;
import com.SkyRise.SkyRise_express.service.EmailNotificationService;
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

@WebMvcTest(EmailNotificationController.class)
public class EmailNotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailNotificationService emailNotificationService;

    @Test
    void createEmailNotification_shouldReturnCreatedNotification() throws Exception {
        EmailNotification notification = new EmailNotification();
        // Set properties for notification

        when(emailNotificationService.createEmailNotification(any(EmailNotification.class))).thenReturn(notification);

        mockMvc.perform(post("/api/emailnotifications")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual notification JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.notificationId").exists()); // Add more assertions
    }

    @Test
    void getEmailNotificationById_shouldReturnNotification() throws Exception {
        Long notificationId = 1L;
        EmailNotification notification = new EmailNotification();
        notification.setNotificationId(notificationId);
        // Set other properties for notification

        when(emailNotificationService.getEmailNotificationById(notificationId)).thenReturn(Optional.of(notification));

        mockMvc.perform(get("/api/emailnotifications/{id}", notificationId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void getAllEmailNotifications_shouldReturnListOfNotifications() throws Exception {
        EmailNotification notification1 = new EmailNotification();
        EmailNotification notification2 = new EmailNotification();
        // Set properties for notifications

        when(emailNotificationService.getAllEmailNotifications()).thenReturn(Arrays.asList(notification1, notification2));

        mockMvc.perform(get("/api/emailnotifications"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateEmailNotification_shouldReturnUpdatedNotification() throws Exception {
        Long notificationId = 1L;
        EmailNotification notification = new EmailNotification();
        notification.setNotificationId(notificationId);
        // Set properties for notification

        when(emailNotificationService.updateEmailNotification(any(EmailNotification.class))).thenReturn(notification);

        mockMvc.perform(put("/api/emailnotifications/{id}", notificationId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated notification JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void deleteEmailNotification_shouldReturnNoContent() throws Exception {
        Long notificationId = 1L;

        mockMvc.perform(delete("/api/emailnotifications/{id}", notificationId))
               .andExpect(status().isNoContent());
    }
} 