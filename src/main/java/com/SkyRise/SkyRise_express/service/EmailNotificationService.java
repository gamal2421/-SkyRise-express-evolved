package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.EmailNotification;

import java.util.List;
import java.util.Optional;

public interface EmailNotificationService {

    EmailNotification createEmailNotification(EmailNotification emailNotification);

    Optional<EmailNotification> getEmailNotificationById(Long id);

    List<EmailNotification> getAllEmailNotifications();

    EmailNotification updateEmailNotification(EmailNotification emailNotification);

    void deleteEmailNotification(Long id);

    // Add other relevant methods based on your business logic
} 