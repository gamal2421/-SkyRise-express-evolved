package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "booking")
public class Booking {

    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
    private LocalDate passengerDob;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;
    
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;
    
    @Column(name = "seat_number")
    private String seatNumber;
    
    @Column(name = "special_request")
    private String specialRequest;
    
    @Column(name = "class", nullable = false) // Matches the database column name
    private String classType;
    
    @Column(nullable = false)
    private String status;
    
    @Column(name = "e_ticket_number", unique = true, nullable = false)
    private String eTicketNumber;
    
    @Column(name = "no_passengers", nullable = false)
    private Integer noPassengers;

    // Constructors
    public Booking() {
        this.bookingDate = LocalDateTime.now();
        this.status = "Confirmed";
        this.noPassengers = 1;
    }

    // Getters and Setters
    public Long getBookingId() { 
        return bookingId; 
    }
    
    public void setBookingId(Long bookingId) { 
        this.bookingId = bookingId; 
    }
    
    public User getUser() { 
        return user; 
    }
    
    public void setUser(User user) { 
        this.user = user; 
    }
    
    public Flight getFlight() { 
        return flight; 
    }
    
    public void setFlight(Flight flight) { 
        this.flight = flight; 
    }
    
    public Payment getPayment() { 
        return payment; 
    }
    
    public void setPayment(Payment payment) { 
        this.payment = payment; 
    }
    
    public LocalDateTime getBookingDate() { 
        return bookingDate; 
    }
    
    public void setBookingDate(LocalDateTime bookingDate) { 
        this.bookingDate = bookingDate; 
    }
    
    public String getSeatNumber() { 
        return seatNumber; 
    }
    
    public void setSeatNumber(String seatNumber) { 
        this.seatNumber = seatNumber; 
    }
    
    public String getSpecialRequest() { 
        return specialRequest; 
    }
    
    public void setSpecialRequest(String specialRequest) { 
        this.specialRequest = specialRequest; 
    }
    
    public String getClassType() { 
        return classType; 
    }
    
    public void setClassType(String classType) { 
        this.classType = classType; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public String getETicketNumber() { 
        return eTicketNumber; 
    }
    
    public void setETicketNumber(String eTicketNumber) { 
        this.eTicketNumber = eTicketNumber; 
    }
    
    public Integer getNoPassengers() { 
        return noPassengers; 
    }
    
    public void setNoPassengers(Integer noPassengers) { 
        this.noPassengers = noPassengers; 
    }

    // Helper method to create a new booking
    public static Booking createNewBooking(User user, Flight flight, Payment payment) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setPayment(payment);
        booking.setClassType("Economy");
        booking.setETicketNumber(generateETicketNumber());
        return booking;
    }

        // Getters and Setters
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public LocalDate getPassengerDob() {
        return passengerDob;
    }

    public void setPassengerDob(LocalDate passengerDob) {
        this.passengerDob = passengerDob;
    }
    private static String generateETicketNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}