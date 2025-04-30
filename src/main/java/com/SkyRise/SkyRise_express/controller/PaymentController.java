package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.*;
import com.SkyRise.SkyRise_express.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showPaymentPage(HttpSession session, Model model) {
        Long flightId = (Long) session.getAttribute("flightId");
        if (flightId == null) {
            return "redirect:/";
        }
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        model.addAttribute("flight", flight);
        return "pages/payment";
    }

    @PostMapping("/process")
    @Transactional
    public String processPayment(@RequestParam String cardNumber,
                               @RequestParam String cardHolder,
                               @RequestParam String expiryDate,
                               @RequestParam String cvv,
                               HttpSession session,
                               Model model) {
        try {
            // Validate payment details
            validatePaymentDetails(cardNumber, expiryDate, cvv);

            // Create and save payment
            Payment payment = new Payment();
            payment.setCardNumber(cardNumber.substring(cardNumber.length() - 4));
            LocalDate expiry = LocalDate.parse("01/" + expiryDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
            payment.setCardExpiry(java.sql.Date.valueOf(expiry));
            payment.setPaymentStatus("Completed");
            payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
            Payment savedPayment = paymentRepository.save(payment);

          User user = userRepository.findByEmail("guest@example.com")
    .orElseGet(() -> {
        User tempUser = new User();
        tempUser.setEmail("guest@example.com");
        tempUser.setFullName((String) session.getAttribute("passengerName"));
        tempUser.setPhone((String) session.getAttribute("passengerPhone"));
        tempUser.setDob(LocalDate.parse((String) session.getAttribute("passengerDob"))); // Add this line
        // Set other required fields if needed
        tempUser.setRole("User"); // Example, set a default role
        tempUser.setPassword(UUID.randomUUID().toString()); // Set a random password for guest users
        return userRepository.save(tempUser);
    });

            Long flightId = (Long) session.getAttribute("flightId");
            if (flightId == null) {
                throw new RuntimeException("Flight not selected");
            }

            Flight flight = flightRepository.findById(flightId).orElseThrow();
            if (flight.getAvailableSeats() <= 0) throw new RuntimeException("No seats available");
            
            // Update flight seats
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepository.save(flight);

            // Create booking
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setFlight(flight);
            booking.setPayment(savedPayment);
            booking.setBookingDate(java.time.LocalDateTime.now());
            booking.setStatus("Confirmed");
            booking.setClassType("Economy");
            booking.setETicketNumber(generateETicketNumber());
            booking.setNoPassengers(1);
            booking.setSpecialRequest("N/A");
            booking.setSeatNumber("AUTO");
            bookingRepository.save(booking);

            // Clear session attributes
            session.removeAttribute("flightId");
            session.removeAttribute("passengerName");
            session.removeAttribute("passengerEmail");
            session.removeAttribute("passengerPhone");

            return "redirect:/booking/confirmation/" + booking.getETicketNumber();

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/payment?error=true";
        }
    }

    private void validatePaymentDetails(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber == null || cardNumber.replaceAll("\\s+", "").length() != 16)
            throw new RuntimeException("Invalid card number (16 digits required)");
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])\\/[0-9]{2}"))
            throw new RuntimeException("Invalid expiry date (MM/YY format required)");
        if (cvv == null || !cvv.matches("\\d{3,4}"))
            throw new RuntimeException("Invalid CVV (3-4 digits required)");
    }

    private String generateETicketNumber() {
        return "ET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}