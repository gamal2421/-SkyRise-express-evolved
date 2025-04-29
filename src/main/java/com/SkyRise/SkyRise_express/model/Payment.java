package com.SkyRise.SkyRise_express.model;

import java.security.Timestamp;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    
    private String cardNumber;
    private Date cardExpiry;
    private String billingAddress;
    private String paymentStatus;
    private Timestamp paymentDate;
    
    // Getters and setters
} 
