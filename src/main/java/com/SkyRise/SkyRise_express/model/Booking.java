package com.SkyRise.SkyRise_express.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    private Timestamp bookingDate;
    private String seatNumber;
    private String specialRequest;
    private String classType;
    private String status;
    @Column(unique = true)
    private String eTicketNumber;
    private Integer noPassengers;
    
    // Getters and setters
}
