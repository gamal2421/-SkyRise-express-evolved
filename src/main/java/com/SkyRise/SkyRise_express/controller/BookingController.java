package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.*;
import com.SkyRise.SkyRise_express.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    private FlightRepository flightRepository;


    @GetMapping("/select/{id}")
    public String selectFlight(@PathVariable("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID: " + id));
        model.addAttribute("selectedFlight", flight);
        return "pages/passenger-details";
    }
@PostMapping("/passenger-details")
public String processPassengerDetails(@RequestParam Long flightId,
                                    @RequestParam String fullName,
                                    @RequestParam String dob,
                                    @RequestParam String email,
                                    @RequestParam String phone,
                                    @RequestParam(required = false) String passportNumber,
                                    @RequestParam String classType,
                                    @RequestParam(required = false) String specialRequest,
                                    HttpSession session,
                                    Model model) {
                                        String dbClassType = classType;
                                        if ("Business Class".equals(classType)) {
                                            dbClassType = "Business";
                                        } else if ("Premium Economy".equals(classType)) {
                                            dbClassType = "Economy"; // or handle differently
                                        } else if ("First Class".equals(classType)) {
                                            dbClassType = "Business"; // or handle differently
                                        }
                                        
    session.setAttribute("passengerName", fullName);
    session.setAttribute("passengerEmail", email);
    session.setAttribute("passengerPhone", phone);
    session.setAttribute("passengerDob", dob);  // Add this line
    session.setAttribute("flightId", flightId);
    session.setAttribute("specialRequest", specialRequest);
    session.setAttribute("classType", dbClassType);

    return "redirect:/payment";
}
}