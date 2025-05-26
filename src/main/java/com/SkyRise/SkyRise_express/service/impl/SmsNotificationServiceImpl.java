package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.SmsNotification;
import com.SkyRise.SkyRise_express.repository.SmsNotificationRepository;
import com.SkyRise.SkyRise_express.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmsNotificationServiceImpl implements SmsNotificationService {

    private final SmsNotificationRepository smsNotificationRepository;

    @Autowired
    public SmsNotificationServiceImpl(SmsNotificationRepository smsNotificationRepository) {
        this.smsNotificationRepository = smsNotificationRepository;
    }

    @Override
    public SmsNotification createSmsNotification(SmsNotification smsNotification) {
        return smsNotificationRepository.save(smsNotification);
    }

    @Override
    public Optional<SmsNotification> getSmsNotificationById(Long id) {
        return smsNotificationRepository.findById(id);
    }

    @Override
    public List<SmsNotification> getAllSmsNotifications() {
        return smsNotificationRepository.findAll();
    }

    @Override
    public SmsNotification updateSmsNotification(SmsNotification smsNotification) {
        // You might want to add logic here to check if the smsNotification exists before updating
        return smsNotificationRepository.save(smsNotification);
    }

    @Override
    public void deleteSmsNotification(Long id) {
        smsNotificationRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 