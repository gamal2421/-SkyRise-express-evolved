package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
} 