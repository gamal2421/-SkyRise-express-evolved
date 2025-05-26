package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import java.util.Date;
import java.util.List;

@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Assuming a foreign key column named customer_id
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "starting_airport_id") // Assuming a foreign key column named starting_airport_id
    private Airport startingAirport;

    @ManyToOne
    @JoinColumn(name = "final_airport_id") // Assuming a foreign key column named final_airport_id
    private Airport finalAirport;

    private Date creationDate;

    @OneToMany(mappedBy = "itinerary")
    private List<Reservation> reservations;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Airport getStartingAirport() {
        return startingAirport;
    }

    public void setStartingAirport(Airport startingAirport) {
        this.startingAirport = startingAirport;
    }

    public Airport getFinalAirport() {
        return finalAirport;
    }

    public void setFinalAirport(Airport finalAirport) {
        this.finalAirport = finalAirport;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
} 