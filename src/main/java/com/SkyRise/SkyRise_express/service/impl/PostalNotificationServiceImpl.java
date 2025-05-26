package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.PostalNotification;
import com.SkyRise.SkyRise_express.repository.PostalNotificationRepository;
import com.SkyRise.SkyRise_express.service.PostalNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostalNotificationServiceImpl implements PostalNotificationService {

    private final PostalNotificationRepository postalNotificationRepository;

    @Autowired
    public PostalNotificationServiceImpl(PostalNotificationRepository postalNotificationRepository) {
        this.postalNotificationRepository = postalNotificationRepository;
    }

    @Override
    public PostalNotification createPostalNotification(PostalNotification postalNotification) {
        return postalNotificationRepository.save(postalNotification);
    }

    @Override
    public Optional<PostalNotification> getPostalNotificationById(Long id) {
        return postalNotificationRepository.findById(id);
    }

    @Override
    public List<PostalNotification> getAllPostalNotifications() {
        return postalNotificationRepository.findAll();
    }

    @Override
    public PostalNotification updatePostalNotification(PostalNotification postalNotification) {
        // You might want to add logic here to check if the postalNotification exists before updating
        return postalNotificationRepository.save(postalNotification);
    }

    @Override
    public void deletePostalNotification(Long id) {
        postalNotificationRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 