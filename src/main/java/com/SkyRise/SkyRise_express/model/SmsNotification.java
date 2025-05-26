package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;

@Entity
public class SmsNotification extends Notification {

    private String phoneNumber;

    // Getters and Setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean send() {
        // TODO: Implement SMS sending logic
        System.out.println("Sending SMS notification to phone number: " + this.phoneNumber);
        return true; // Assuming successful send for now
    }
} 