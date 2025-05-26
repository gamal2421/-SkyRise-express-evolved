package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
} 