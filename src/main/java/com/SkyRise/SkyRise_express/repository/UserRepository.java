package com.SkyRise.SkyRise_express.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SkyRise.SkyRise_express.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}