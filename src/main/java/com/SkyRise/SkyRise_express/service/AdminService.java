package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin createAdmin(Admin admin);

    Optional<Admin> getAdminById(Long id);

    List<Admin> getAllAdmins();

    Admin updateAdmin(Admin admin);

    void deleteAdmin(Long id);

    // Add other relevant methods based on your business logic
} 