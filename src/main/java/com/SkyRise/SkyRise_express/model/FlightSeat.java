package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reservationNumber; // This seems redundant if linked to Reservation entity

    @ManyToOne
    @JoinColumn(name = "flight_instance_id") // Assuming a foreign key column named flight_instance_id
    private FlightInstance flightInstance;

    @ManyToOne // Changed from OneToOne based on common data models
    @JoinColumn(name = "seat_id") // Assuming a foreign key column named seat_id
    private Seat seat;

    @OneToOne(mappedBy = "flightSeat")
    private Reservation reservation;

    private boolean isAvailable; // Added isAvailable field
    private String seatNumber; // Added seatNumber field

    @Enumerated(EnumType.STRING)
    private SeatType seatType; // Added seatType field

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public FlightInstance getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(FlightInstance flightInstance) {
        this.flightInstance = flightInstance;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public boolean isAvailable() { // Added getter for isAvailable (using is for boolean)
        return isAvailable;
    }

    public void setAvailable(boolean available) { // Added setter for isAvailable
        isAvailable = available;
    }

    public String getSeatNumber() { // Added getter for seatNumber
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) { // Added setter for seatNumber
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType() { // Added getter for seatType
        return seatType;
    }

    public void setSeatType(SeatType seatType) { // Added setter for seatType
        this.seatType = seatType;
    }
} 