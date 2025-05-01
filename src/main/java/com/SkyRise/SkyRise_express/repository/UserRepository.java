package com.SkyRise.SkyRise_express.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SkyRise.SkyRise_express.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email); // Needed for registration check
    List<User> findByRole(String role);
}
