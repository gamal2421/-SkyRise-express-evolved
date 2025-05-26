package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.Airline;
import com.SkyRise.SkyRise_express.model.Airport;
import com.SkyRise.SkyRise_express.service.FlightService;
import com.SkyRise.SkyRise_express.service.AirlineService;
import com.SkyRise.SkyRise_express.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/flights")
public class FlightAdminController {

    private final FlightService flightService;
    private final AirlineService airlineService;
    private final AirportService airportService;

    @Autowired
    public FlightAdminController(FlightService flightService, AirlineService airlineService, AirportService airportService) {
        this.flightService = flightService;
        this.airlineService = airlineService;
        this.airportService = airportService;
    }

    @GetMapping
    public String listFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "admin/listFlights";
    }

    @GetMapping("/new")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("airports", airportService.getAllAirports());
        return "admin/addFlight";
    }

    @PostMapping("/new")
    public String addFlight(@ModelAttribute("flight") Flight flight,
                              @RequestParam("airline") Long airlineId,
                              @RequestParam("departureAirport") Long departureAirportId,
                              @RequestParam("arrivalAirport") Long arrivalAirportId,
                              RedirectAttributes redirectAttributes) {

        Optional<Airline> airlineOptional = airlineService.getAirlineById(airlineId);
        Optional<Airport> departureAirportOptional = airportService.getAirportById(departureAirportId);
        Optional<Airport> arrivalAirportOptional = airportService.getAirportById(arrivalAirportId);

        if (airlineOptional.isEmpty() || departureAirportOptional.isEmpty() || arrivalAirportOptional.isEmpty()) {
             redirectAttributes.addFlashAttribute("error", "Invalid airline or airport selected.");
             return "redirect:/admin/flights/new"; // Redirect back to the form with an error
        }

        flight.setAirline(airlineOptional.get());
        flight.setDepartureAirport(departureAirportOptional.get());
        flight.setArrivalAirport(arrivalAirportOptional.get());

        flightService.createFlight(flight);
        redirectAttributes.addFlashAttribute("success", "Flight added successfully!");
        return "redirect:/admin/flights"; // Redirect to the flight list
    }

    @GetMapping("/{id}")
    public String showFlightDetails(@PathVariable("id") Long id, Model model) {
        Optional<Flight> flightOptional = flightService.getFlightById(id);
        if (flightOptional.isPresent()) {
            model.addAttribute("flight", flightOptional.get());
            return "admin/viewFlight"; // refers to src/main/resources/templates/admin/viewFlight.html
        } else {
            // Handle flight not found
            return "redirect:/admin/flights";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditFlightForm(@PathVariable("id") Long id, Model model) {
        Optional<Flight> flightOptional = flightService.getFlightById(id);
        if (flightOptional.isPresent()) {
            model.addAttribute("flight", flightOptional.get());
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airports", airportService.getAllAirports());
            return "admin/addFlight"; // Reuse the add flight form for editing
        } else {
            return "redirect:/admin/flights";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateFlight(@PathVariable("id") Long id, 
                               @ModelAttribute("flight") Flight flight,
                               @RequestParam("airline") Long airlineId,
                               @RequestParam("departureAirport") Long departureAirportId,
                               @RequestParam("arrivalAirport") Long arrivalAirportId,
                               RedirectAttributes redirectAttributes) {

        Optional<Airline> airlineOptional = airlineService.getAirlineById(airlineId);
        Optional<Airport> departureAirportOptional = airportService.getAirportById(departureAirportId);
        Optional<Airport> arrivalAirportOptional = airportService.getAirportById(arrivalAirportId);

        if (airlineOptional.isEmpty() || departureAirportOptional.isEmpty() || arrivalAirportOptional.isEmpty()) {
             redirectAttributes.addFlashAttribute("error", "Invalid airline or airport selected.");
             return "redirect:/admin/flights/edit/" + id; // Redirect back to the edit form with an error
        }

        flight.setId(id); // Ensure the ID from the path is set on the flight object
        flight.setAirline(airlineOptional.get());
        flight.setDepartureAirport(departureAirportOptional.get());
        flight.setArrivalAirport(arrivalAirportOptional.get());

        flightService.updateFlight(flight);
        redirectAttributes.addFlashAttribute("success", "Flight updated successfully!");
        return "redirect:/admin/flights"; // Redirect back to the flight list
    }

    @PostMapping("/delete/{id}")
    public String deleteFlight(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        flightService.deleteFlight(id);
        redirectAttributes.addFlashAttribute("success", "Flight deleted successfully!");
        return "redirect:/admin/flights";
    }
} 