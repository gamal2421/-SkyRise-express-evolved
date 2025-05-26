package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;

@Entity
public class EmailNotification extends Notification {

    private String email;

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean send() {
        // TODO: Implement email sending logic
        System.out.println("Sending email notification to: " + this.email);
        return true; // Assuming successful send for now
    }
} 