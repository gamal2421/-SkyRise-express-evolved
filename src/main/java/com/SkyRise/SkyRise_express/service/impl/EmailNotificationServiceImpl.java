package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.EmailNotification;
import com.SkyRise.SkyRise_express.repository.EmailNotificationRepository;
import com.SkyRise.SkyRise_express.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final EmailNotificationRepository emailNotificationRepository;

    @Autowired
    public EmailNotificationServiceImpl(EmailNotificationRepository emailNotificationRepository) {
        this.emailNotificationRepository = emailNotificationRepository;
    }

    @Override
    public EmailNotification createEmailNotification(EmailNotification emailNotification) {
        return emailNotificationRepository.save(emailNotification);
    }

    @Override
    public Optional<EmailNotification> getEmailNotificationById(Long id) {
        return emailNotificationRepository.findById(id);
    }

    @Override
    public List<EmailNotification> getAllEmailNotifications() {
        return emailNotificationRepository.findAll();
    }

    @Override
    public EmailNotification updateEmailNotification(EmailNotification emailNotification) {
        // You might want to add logic here to check if the emailNotification exists before updating
        return emailNotificationRepository.save(emailNotification);
    }

    @Override
    public void deleteEmailNotification(Long id) {
        emailNotificationRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 