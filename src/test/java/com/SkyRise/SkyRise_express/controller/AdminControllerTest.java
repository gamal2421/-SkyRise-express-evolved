package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Admin;
import com.SkyRise.SkyRise_express.service.AdminService;
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

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Test
    void createAdmin_shouldReturnCreatedAdmin() throws Exception {
        Admin admin = new Admin();
        // Set properties for admin

        when(adminService.createAdmin(any(Admin.class))).thenReturn(admin);

        mockMvc.perform(post("/api/admins")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual admin JSON
               .andExpect(status().isCreated());
               // .andExpect(jsonPath("$.id").exists()); // Add more assertions
    }

    @Test
    void getAdminById_shouldReturnAdmin() throws Exception {
        Long adminId = 1L;
        Admin admin = new Admin();
        admin.setId(adminId);
        // Set other properties for admin

        when(adminService.getAdminById(adminId)).thenReturn(Optional.of(admin));

        mockMvc.perform(get("/api/admins/{id}", adminId))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(adminId)); // Add more assertions
    }

    @Test
    void getAllAdmins_shouldReturnListOfAdmins() throws Exception {
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        // Set properties for admins

        when(adminService.getAllAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        mockMvc.perform(get("/api/admins"))
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.size()").value(2)); // Add more assertions
    }

    @Test
    void updateAdmin_shouldReturnUpdatedAdmin() throws Exception {
        Long adminId = 1L;
        Admin admin = new Admin();
        admin.setId(adminId);
        // Set properties for admin

        when(adminService.updateAdmin(any(Admin.class))).thenReturn(admin);

        mockMvc.perform(put("/api/admins/{id}", adminId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{}")) // Replace with actual updated admin JSON
               .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(adminId)); // Add more assertions
    }

    @Test
    void deleteAdmin_shouldReturnNoContent() throws Exception {
        Long adminId = 1L;

        mockMvc.perform(delete("/api/admins/{id}", adminId))
               .andExpect(status().isNoContent());
    }
} 