package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.PostalNotification;

import java.util.List;
import java.util.Optional;

public interface PostalNotificationService {

    PostalNotification createPostalNotification(PostalNotification postalNotification);

    Optional<PostalNotification> getPostalNotificationById(Long id);

    List<PostalNotification> getAllPostalNotifications();

    PostalNotification updatePostalNotification(PostalNotification postalNotification);

    void deletePostalNotification(Long id);

    // Add other relevant methods based on your business logic
} 