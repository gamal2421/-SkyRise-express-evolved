package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Booking;
import com.SkyRise.SkyRise_express.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
   
    List<Booking> findByFlight_FlightId(Long flightId);
@Query("SELECT b FROM Booking b LEFT JOIN FETCH b.flight LEFT JOIN FETCH b.user WHERE b.eTicketNumber = :eTicketNumber")
Optional<Booking> findByETicketNumber(@Param("eTicketNumber") String eTicketNumber);
}