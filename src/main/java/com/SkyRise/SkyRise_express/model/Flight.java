package com.SkyRise.SkyRise_express.model;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
// Use this import for Jakarta EE 9+ (recommended)
import jakarta.validation.constraints.*;

// OR if you're using older javax namespace
// import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;
    
    @NotBlank(message = "Flight number is required")
    @Size(min = 3, max = 10, message = "Flight number must be between 3 and 10 characters")
    @Column(name = "flight_number", nullable = false, length = 10)
    private String flightNumber;
    
    @NotBlank(message = "Departure airport is required")
    @Size(min = 3, max = 5, message = "Airport code must be 3-5 characters")
    @Column(name = "from_airport", nullable = false, length = 5)
    private String fromAirport;
    
    @NotBlank(message = "Arrival airport is required")
    @Size(min = 3, max = 5, message = "Airport code must be 3-5 characters")
    @Column(name = "to_airport", nullable = false, length = 5)
    private String toAirport;
    
    @NotNull(message = "Departure date is required")
    @FutureOrPresent(message = "Departure date must be in the present or future")
    @Column(name = "departure_date", nullable = false)
    private Date departureDate;
    
    @FutureOrPresent(message = "Return date must be in the present or future")
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "departure_time", columnDefinition = "TIME")
    private LocalTime departureTime;

    @Column(name = "arrival_time", columnDefinition = "TIME")
    private LocalTime arrivalTime;
    @NotBlank(message = "Airline is required")
    @Size(max = 50, message = "Airline name must be less than 50 characters")
    @Column(name = "airline", nullable = false, length = 50)
    private String airline;
    
    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Total seats must be at least 1")
    @Max(value = 1000, message = "Total seats cannot exceed 1000")
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;
    
    @NotNull(message = "Available seats is required")
    @Min(value = 0, message = "Available seats cannot be negative")
    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;
    
    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be positive")
    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    // Transient field for duration calculation


    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

public void setDepartureTime(LocalTime departureTime) {
    this.departureTime = departureTime;
}



public void setArrivalTime(LocalTime arrivalTime) {
    this.arrivalTime = arrivalTime;
}


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

 



 


    // Business logic method to check if flight is available
    @Transient
    public boolean isAvailable() {
        return availableSeats != null && availableSeats > 0;
    }
}