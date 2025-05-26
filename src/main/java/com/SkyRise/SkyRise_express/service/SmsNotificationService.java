package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.SmsNotification;

import java.util.List;
import java.util.Optional;

public interface SmsNotificationService {

    SmsNotification createSmsNotification(SmsNotification smsNotification);

    Optional<SmsNotification> getSmsNotificationById(Long id);

    List<SmsNotification> getAllSmsNotifications();

    SmsNotification updateSmsNotification(SmsNotification smsNotification);

    void deleteSmsNotification(Long id);

    // Add other relevant methods based on your business logic
} 