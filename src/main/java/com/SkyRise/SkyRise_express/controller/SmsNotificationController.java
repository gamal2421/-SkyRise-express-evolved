package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.SmsNotification;
import com.SkyRise.SkyRise_express.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/smsnotifications")
public class SmsNotificationController {

    private final SmsNotificationService smsNotificationService;

    @Autowired
    public SmsNotificationController(SmsNotificationService smsNotificationService) {
        this.smsNotificationService = smsNotificationService;
    }

    @PostMapping
    public ResponseEntity<SmsNotification> createSmsNotification(@RequestBody SmsNotification smsNotification) {
        SmsNotification createdSmsNotification = smsNotificationService.createSmsNotification(smsNotification);
        return new ResponseEntity<>(createdSmsNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmsNotification> getSmsNotificationById(@PathVariable Long id) {
        Optional<SmsNotification> smsNotification = smsNotificationService.getSmsNotificationById(id);
        return smsNotification.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SmsNotification>> getAllSmsNotifications() {
        List<SmsNotification> smsNotifications = smsNotificationService.getAllSmsNotifications();
        return new ResponseEntity<>(smsNotifications, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmsNotification> updateSmsNotification(@PathVariable Long id, @RequestBody SmsNotification smsNotification) {
        // You might want to add logic here to check if the smsNotification with id exists before updating
        smsNotification.setNotificationId(id); // Ensure the correct ID is set for update
        SmsNotification updatedSmsNotification = smsNotificationService.updateSmsNotification(smsNotification);
        return ResponseEntity.ok(updatedSmsNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmsNotification(@PathVariable Long id) {
        smsNotificationService.deleteSmsNotification(id);
        return ResponseEntity.noContent().build();
    }
} 