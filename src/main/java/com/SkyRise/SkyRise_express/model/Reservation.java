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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNumber;

    @ManyToOne
    @JoinColumn(name = "itinerary_id") // Assuming a foreign key column named itinerary_id
    private Itinerary itinerary;

    @ManyToOne
    @JoinColumn(name = "flight_instance_id") // Assuming a foreign key column named flight_instance_id
    private FlightInstance flightInstance;

    @OneToOne
    @JoinColumn(name = "flight_seat_id") // Assuming a foreign key column named flight_seat_id
    private FlightSeat flightSeat;

    @ManyToOne
    @JoinColumn(name = "passenger_id") // Assuming a foreign key column named passenger_id
    private Passenger passenger;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // Getters and Setters

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public FlightInstance getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(FlightInstance flightInstance) {
        this.flightInstance = flightInstance;
    }

    public FlightSeat getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(FlightSeat flightSeat) {
        this.flightSeat = flightSeat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
} 