package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.EmailNotification;
import com.SkyRise.SkyRise_express.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emailnotifications")
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public EmailNotificationController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping
    public ResponseEntity<EmailNotification> createEmailNotification(@RequestBody EmailNotification emailNotification) {
        EmailNotification createdEmailNotification = emailNotificationService.createEmailNotification(emailNotification);
        return new ResponseEntity<>(createdEmailNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailNotification> getEmailNotificationById(@PathVariable Long id) {
        Optional<EmailNotification> emailNotification = emailNotificationService.getEmailNotificationById(id);
        return emailNotification.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmailNotification>> getAllEmailNotifications() {
        List<EmailNotification> emailNotifications = emailNotificationService.getAllEmailNotifications();
        return new ResponseEntity<>(emailNotifications, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailNotification> updateEmailNotification(@PathVariable Long id, @RequestBody EmailNotification emailNotification) {
        // You might want to add logic here to check if the emailNotification with id exists before updating
        emailNotification.setNotificationId(id); // Ensure the correct ID is set for update
        EmailNotification updatedEmailNotification = emailNotificationService.updateEmailNotification(emailNotification);
        return ResponseEntity.ok(updatedEmailNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailNotification(@PathVariable Long id) {
        emailNotificationService.deleteEmailNotification(id);
        return ResponseEntity.noContent().build();
    }
} 