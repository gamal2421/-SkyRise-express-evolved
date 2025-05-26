package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Crew extends Person {

    @ManyToOne
    @JoinColumn(name = "flight_instance_id") // Assuming a foreign key column named flight_instance_id
    private FlightInstance flightInstance;

    // Crew specific properties or methods can be added here later if needed

    // Getter and Setter for flightInstance

    public FlightInstance getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(FlightInstance flightInstance) {
        this.flightInstance = flightInstance;
    }
} 