package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.*;
import com.SkyRise.SkyRise_express.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
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
    public String showPaymentPage(HttpSession session, Model model,
                                @RequestParam(required = false) String error) {
        Long flightId = (Long) session.getAttribute("flightId");
        if (flightId == null) {
            return "redirect:/";
        }
        
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            return "redirect:/";
        }
        
        model.addAttribute("flight", flight);
        
        // Add error message if present
        if (error != null) {
            model.addAttribute("error", "Payment failed. Please check your details and try again.");
        }
        
        return "pages/payment";
    }


    


    @PostMapping("/process")
    @Transactional
    public String processPayment(@RequestParam String cardNumber,
                               @RequestParam String cardHolder,
                               @RequestParam String expiryDate,
                               @RequestParam String cvv,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            // Validate payment details
            validatePaymentDetails(cardNumber, expiryDate, cvv);

            // Get user from session
            User user = (User) session.getAttribute("user");
            String passengerName = (String) session.getAttribute("passengerName");
            String passengerPhone = (String) session.getAttribute("passengerPhone");
            String passengerDobStr = (String) session.getAttribute("passengerDob");
            String passengerEmail = (String) session.getAttribute("passengerEmail");
            
            if (user == null) {
                // Guest booking flow — check or create temp user by email
                user = userRepository.findByEmail(passengerEmail)
                    .orElseGet(() -> {
                        User tempUser = new User();
                        tempUser.setEmail(passengerEmail);
                        tempUser.setFullName(passengerName);
                        tempUser.setPhone(passengerPhone);
                        tempUser.setDob(LocalDate.parse(passengerDobStr));
                        tempUser.setRole("User");
                        tempUser.setPassword(UUID.randomUUID().toString());
                        return userRepository.save(tempUser);
                    });
            }
            

            // Get flight from session
            Long flightId = (Long) session.getAttribute("flightId");
            if (flightId == null) {
                throw new RuntimeException("Flight not selected");
            }

            Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
            
            if (flight.getAvailableSeats() <= 0) {
                throw new RuntimeException("No seats available");
            }

            // Create and save payment
            Payment payment = new Payment();
            payment.setCardNumber(maskCardNumber(cardNumber));
            payment.setCardExpiry(parseExpiryDate(expiryDate));
            payment.setPaymentStatus("Completed");
            payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
            
            Payment savedPayment = paymentRepository.save(payment);

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
            booking.setSeatNumber(null);

            // Add passenger info to booking (if your Booking entity has these fields)
            booking.setPassengerName(passengerName);
            booking.setPassengerEmail(passengerEmail);
            booking.setPassengerPhone(passengerPhone);
            booking.setPassengerDob(LocalDate.parse(passengerDobStr));
            
            Booking savedBooking = bookingRepository.save(booking);
            String assignedSeat = savedBooking.getSeatNumber(); // this should now contain e.g., "B3"
            booking.setSeatNumber(assignedSeat);

            // Clear session attributes
            session.removeAttribute("flightId");

            return "redirect:/booking/confirmation/" + booking.getETicketNumber();

        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/payment";
        }
    }

    private void validatePaymentDetails(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber == null || cardNumber.replaceAll("\\s+", "").length() != 16) {
            throw new RuntimeException("Invalid card number (16 digits required)");
        }
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])\\/([0-9]{2})")) {
            throw new RuntimeException("Invalid expiry date (MM/YY format required)");
        }
        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            throw new RuntimeException("Invalid CVV (3-4 digits required)");
        }
    }

    private String maskCardNumber(String cardNumber) {
        String cleaned = cardNumber.replaceAll("\\s+", "");
        return "****-****-****-" + cleaned.substring(cleaned.length() - 4);
    }

    private java.sql.Date parseExpiryDate(String expiryDate) throws DateTimeParseException {
        String[] parts = expiryDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = 2000 + Integer.parseInt(parts[1]);
        
        // Set to last day of month
        LocalDate date = LocalDate.of(year, month, 1)
            .plusMonths(1)
            .minusDays(1);
            
        return java.sql.Date.valueOf(date);
    }

    private String generateETicketNumber() {
        return "ET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}