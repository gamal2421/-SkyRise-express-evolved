package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.PostalNotification;
import com.SkyRise.SkyRise_express.service.PostalNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postalnotifications")
public class PostalNotificationController {

    private final PostalNotificationService postalNotificationService;

    @Autowired
    public PostalNotificationController(PostalNotificationService postalNotificationService) {
        this.postalNotificationService = postalNotificationService;
    }

    @PostMapping
    public ResponseEntity<PostalNotification> createPostalNotification(@RequestBody PostalNotification postalNotification) {
        PostalNotification createdPostalNotification = postalNotificationService.createPostalNotification(postalNotification);
        return new ResponseEntity<>(createdPostalNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostalNotification> getPostalNotificationById(@PathVariable Long id) {
        Optional<PostalNotification> postalNotification = postalNotificationService.getPostalNotificationById(id);
        return postalNotification.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PostalNotification>> getAllPostalNotifications() {
        List<PostalNotification> postalNotifications = postalNotificationService.getAllPostalNotifications();
        return new ResponseEntity<>(postalNotifications, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostalNotification> updatePostalNotification(@PathVariable Long id, @RequestBody PostalNotification postalNotification) {
        // You might want to add logic here to check if the postalNotification with id exists before updating
        postalNotification.setNotificationId(id); // Ensure the correct ID is set for update
        PostalNotification updatedPostalNotification = postalNotificationService.updatePostalNotification(postalNotification);
        return ResponseEntity.ok(updatedPostalNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostalNotification(@PathVariable Long id) {
        postalNotificationService.deletePostalNotification(id);
        return ResponseEntity.noContent().build();
    }
} 