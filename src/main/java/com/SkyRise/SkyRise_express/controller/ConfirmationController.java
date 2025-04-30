package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Booking;
import com.SkyRise.SkyRise_express.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/booking")
public class ConfirmationController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/confirmation/{eTicketNumber}")
    public String showConfirmation(@PathVariable String eTicketNumber, Model model) {
        // Fetch booking with flight and user eagerly loaded
        Booking booking = bookingRepository.findByETicketNumber(eTicketNumber)
            .orElseThrow(() -> new IllegalArgumentException("Invalid ticket number"));

        // Add null checks and handle incomplete data
        if (booking.getFlight() == null || booking.getUser() == null) {
            model.addAttribute("errorMessage", "Incomplete booking data. Please try again.");
            return "pages/error"; // You can create an error page to show the message
        }

        // Format dates/times for display in the confirmation page
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Assuming flight has departureDate and departureTime
        String formattedDepartureDate = booking.getFlight().getDepartureDate().toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate()
            .format(dateFormatter);
        
        String formattedDepartureTime = booking.getFlight().getDepartureTime().format(timeFormatter);

        model.addAttribute("booking", booking);
        model.addAttribute("formattedDepartureDate", formattedDepartureDate);
        model.addAttribute("formattedDepartureTime", formattedDepartureTime);
        
        return "pages/confirmation"; // Return the confirmation view
    }
}
