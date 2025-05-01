package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.User;
import com.SkyRise.SkyRise_express.repository.FlightRepository;
import com.SkyRise.SkyRise_express.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FlightRepository flightRepository;
    
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String success,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        if (success != null) {
            model.addAttribute("success", "Registration successful! Please login.");
        }
        return "pages/login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,
                                        @RequestParam String password,
                                        HttpSession session) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                if (user.getRole().equals("Admin")) {
                    return ResponseEntity.ok("admin");
                }
                return ResponseEntity.ok("user");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password.");
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "pages/registration";
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String dateOfBirth,
                            @RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam String password,
                            @RequestParam String reEnterPassword,
                            @RequestParam(required = false) String passportNumber) {

        if (!password.equals(reEnterPassword)) {
            return "redirect:/register?error=Passwords+do+not+match";
        }

        if (userRepository.existsByEmail(email)) {
            return "redirect:/register?error=Email+already+exists";
        }

        User newUser = new User();
        newUser.setFullName(firstName + " " + lastName);
        newUser.setDob(LocalDate.parse(dateOfBirth));
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setPassportNumber(passportNumber);
        newUser.setRole("User");

        userRepository.save(newUser);

        return "redirect:/login?success=registered";
    }

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        if (user.getRole().equals("Admin")) {
            return "redirect:/admin/dashboard";
        }
        
        model.addAttribute("user", user);
        return "pages/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !currentUser.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        
        List<User> allUsers = userRepository.findAll().stream()
                .filter(user -> !user.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("flights", flightRepository.findAll());
        model.addAttribute("currentUser", currentUser);
        
        return "admin/dashboard";
    }
    
    @PostMapping("/admin/flights/add")
    public String addFlight(
            @RequestParam String flightNumber,
            @RequestParam String fromAirport,
            @RequestParam String toAirport,
            @RequestParam(name = "departureDateStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String departureDateStr,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime departureTime,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime arrivalTime,
            @RequestParam String airline,
            @RequestParam Integer totalSeats,
            @RequestParam Integer availableSeats,
            @RequestParam Double basePrice,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("Admin")) {
            return "redirect:/login";
        }

        try {
            Date departureDate = Date.valueOf(departureDateStr);
            
            Flight flight = new Flight();
            flight.setFlightNumber(flightNumber);
            flight.setFromAirport(fromAirport);
            flight.setToAirport(toAirport);
            flight.setDepartureDate(departureDate);
            flight.setReturnDate(returnDate);
            flight.setDepartureTime(departureTime);
            flight.setArrivalTime(arrivalTime);
            flight.setAirline(airline);
            flight.setTotalSeats(totalSeats);
            flight.setAvailableSeats(availableSeats);
            flight.setBasePrice(basePrice);

            flightRepository.save(flight);
            redirectAttributes.addFlashAttribute("success", "Flight added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add flight: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/flights/update/{id}")
    public String updateFlight(
            @PathVariable Long id,
            @RequestParam String flightNumber,
            @RequestParam String fromAirport,
            @RequestParam String toAirport,
            @RequestParam(name = "departureDateStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String departureDateStr,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime departureTime,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime arrivalTime,
            @RequestParam String airline,
            @RequestParam Integer totalSeats,
            @RequestParam Integer availableSeats,
            @RequestParam Double basePrice,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        
        try {
            Date departureDate = Date.valueOf(departureDateStr);
            
            Flight flight = new Flight();
            flight.setFlightId(id);
            flight.setFlightNumber(flightNumber);
            flight.setFromAirport(fromAirport);
            flight.setToAirport(toAirport);
            flight.setDepartureDate(departureDate);
            flight.setReturnDate(returnDate);
            flight.setDepartureTime(departureTime);
            flight.setArrivalTime(arrivalTime);
            flight.setAirline(airline);
            flight.setTotalSeats(totalSeats);
            flight.setAvailableSeats(availableSeats);
            flight.setBasePrice(basePrice);

            flightRepository.save(flight);
            redirectAttributes.addFlashAttribute("success", "Flight updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update flight: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/flights/delete/{id}")
    public String deleteFlight(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        
        flightRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/admin/users/update-role/{id}")
    public String updateUserRole(@PathVariable Long id, @RequestParam String role, HttpSession session) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(role);
            userRepository.save(user);
        }
        
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !admin.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        
        userRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }@GetMapping("/admin/reports")
public String showReports(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null || !user.getRole().equals("Admin")) {
        return "redirect:/login";
    }
    
    // Get sales data
    List<Object[]> monthlySales = flightRepository.getMonthlySales();
    List<ReportData> salesData = monthlySales.stream()
        .map(result -> new ReportData(
            ((String) result[0]).trim(), // Trim whitespace from month names
            ((Number) result[1]).intValue()
        ))
        .collect(Collectors.toList());
    
    // Get destination data (limit to top 5)
    List<Object[]> popularDestinations = flightRepository.getPopularDestinations();
    List<ReportData> destinationData = popularDestinations.stream()
        .limit(5) // Limit to top 5
        .map(result -> new ReportData(
            (String) result[0],
            ((Number) result[1]).intValue()
        ))
        .collect(Collectors.toList());
    
    model.addAttribute("salesData", salesData);
    model.addAttribute("destinationData", destinationData);
    
    return "admin/reports";
}
@GetMapping("/api/reports/sales")
@ResponseBody
public List<ReportData> getSalesData() {
    List<Object[]> monthlySales = flightRepository.getMonthlySales();
    return monthlySales.stream()
        .map(result -> new ReportData(
            (String) result[0],
            ((Number) result[1]).intValue()
        ))
        .collect(Collectors.toList());
}

@GetMapping("/api/reports/destinations")
@ResponseBody
public List<ReportData> getDestinationData() {
    List<Object[]> popularDestinations = flightRepository.getPopularDestinations();
    return popularDestinations.stream()
        .map(result -> new ReportData(
            (String) result[0],
            ((Number) result[1]).intValue()
        ))
        .collect(Collectors.toList());
}
    public static class ReportData {
        private String label;
        private int value;
        
        public ReportData(String label, int value) {
            this.label = label;
            this.value = value;
        }
        
        public String getLabel() {
            return label;
        }
        
        public int getValue() {
            return value;
        }
    }
}