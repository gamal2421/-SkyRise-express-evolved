package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Embedded;

@Entity
public class PostalNotification extends Notification {

    @Embedded
    private Address address;

    // Getters and Setters

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean send() {
        // TODO: Implement postal notification sending logic
        System.out.println("Sending postal notification to address: " + this.address);
        return true; // Assuming successful send for now
    }
} 