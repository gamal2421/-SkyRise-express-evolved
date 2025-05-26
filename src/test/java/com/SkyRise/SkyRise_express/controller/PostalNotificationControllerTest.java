package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.PostalNotification;
import com.SkyRise.SkyRise_express.service.PostalNotificationService;
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

@WebMvcTest(PostalNotificationController.class)
public class PostalNotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostalNotificationService postalNotificationService;

    @Test
    void createPostalNotification_shouldReturnCreatedNotification() throws Exception {
        PostalNotification notification = new PostalNotification();
        // Set properties for notification

        when(postalNotificationService.createPostalNotification(any(PostalNotification.class))).thenReturn(notification);

        mockMvc.perform(post("/api/postalnotifications")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual notification JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.notificationId").exists()); // Add more assertions
    }

    @Test
    void getPostalNotificationById_shouldReturnNotification() throws Exception {
        Long notificationId = 1L;
        PostalNotification notification = new PostalNotification();
        notification.setNotificationId(notificationId);
        // Set other properties for notification

        when(postalNotificationService.getPostalNotificationById(notificationId)).thenReturn(Optional.of(notification));

        mockMvc.perform(get("/api/postalnotifications/{id}", notificationId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void getAllPostalNotifications_shouldReturnListOfNotifications() throws Exception {
        PostalNotification notification1 = new PostalNotification();
        PostalNotification notification2 = new PostalNotification();
        // Set properties for notifications

        when(postalNotificationService.getAllPostalNotifications()).thenReturn(Arrays.asList(notification1, notification2));

        mockMvc.perform(get("/api/postalnotifications"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updatePostalNotification_shouldReturnUpdatedNotification() throws Exception {
        Long notificationId = 1L;
        PostalNotification notification = new PostalNotification();
        notification.setNotificationId(notificationId);
        // Set properties for notification

        when(postalNotificationService.updatePostalNotification(any(PostalNotification.class))).thenReturn(notification);

        mockMvc.perform(put("/api/postalnotifications/{id}", notificationId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated notification JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.notificationId").value(notificationId)); // Add more assertions
    }

    @Test
    void deletePostalNotification_shouldReturnNoContent() throws Exception {
        Long notificationId = 1L;

        mockMvc.perform(delete("/api/postalnotifications/{id}", notificationId))
               .andExpect(status().isNoContent());
    }
} 