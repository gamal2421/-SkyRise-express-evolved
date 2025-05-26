package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "startingAirport")
    private List<Itinerary> departingItineraries;

    @OneToMany(mappedBy = "finalAirport")
    private List<Itinerary> arrivingItineraries;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Itinerary> getDepartingItineraries() {
        return departingItineraries;
    }

    public void setDepartingItineraries(List<Itinerary> departingItineraries) {
        this.departingItineraries = departingItineraries;
    }

    public List<Itinerary> getArrivingItineraries() {
        return arrivingItineraries;
    }

    public void setArrivingItineraries(List<Itinerary> arrivingItineraries) {
        this.arrivingItineraries = arrivingItineraries;
    }
} 