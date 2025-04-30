package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    // Exact match search - works perfectly with your schema
    
@Query("SELECT f FROM Flight f WHERE f.fromAirport = ?1 AND f.toAirport = ?2")
List<Flight> findByRoute(String fromAirport, String toAirport);
    // Enhanced date range query with sorting
    @Query("SELECT f FROM Flight f WHERE UPPER(f.fromAirport) = UPPER(?1) AND UPPER(f.toAirport) = UPPER(?2) AND FUNCTION('DATE', f.departureDate) = ?3")
List<Flight> findByFromAirportAndToAirportAndDepartureDate(String fromAirport, String toAirport, Date departureDate);

    @Query("SELECT DISTINCT f.toAirport FROM Flight f ORDER BY f.toAirport")
    List<String> findDistinctToAirports();

    @Query("SELECT DISTINCT f.fromAirport FROM Flight f ORDER BY f.fromAirport")
    List<String> findDistinctFromAirports();
    
   
    // Available flights with seat threshold
    @Query("SELECT f FROM Flight f WHERE f.availableSeats >= ?1")
    List<Flight> findAvailableFlights(int minSeats);

}