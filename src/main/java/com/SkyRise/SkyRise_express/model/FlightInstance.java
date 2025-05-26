package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class FlightInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date departureDate;
    private LocalTime departureTime;
    private Date arrivalDate;
    private String gate;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ManyToOne
    @JoinColumn(name = "flight_id") // Assuming a foreign key column named flight_id
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "aircraft_id") // Assuming a foreign key column named aircraft_id
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "weekly_schedule_id") // Assuming a foreign key column named weekly_schedule_id
    private WeeklySchedule weeklySchedule;

    @ManyToOne
    @JoinColumn(name = "custom_schedule_id") // Assuming a foreign key column named custom_schedule_id
    private CustomSchedule customSchedule;

    @OneToMany(mappedBy = "flightInstance")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "flightInstance")
    private List<FlightSeat> flightSeats;

    @OneToMany(mappedBy = "flightInstance")
    private List<Crew> assignedCrew;

    @ManyToOne
    @JoinColumn(name = "pilot_id") // Assuming a foreign key column named pilot_id
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "front_desk_officer_id") // Assuming a foreign key column named front_desk_officer_id
    private FrontDeskOfficer frontDeskOfficer;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public CustomSchedule getCustomSchedule() {
        return customSchedule;
    }

    public void setCustomSchedule(CustomSchedule customSchedule) {
        this.customSchedule = customSchedule;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<FlightSeat> getFlightSeats() {
        return flightSeats;
    }

    public void setFlightSeats(List<FlightSeat> flightSeats) {
        this.flightSeats = flightSeats;
    }

    public List<Crew> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<Crew> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public FrontDeskOfficer getFrontDeskOfficer() {
        return frontDeskOfficer;
    }

    public void setFrontDeskOfficer(FrontDeskOfficer frontDeskOfficer) {
        this.frontDeskOfficer = frontDeskOfficer;
    }
} 