package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/index")
    public String showHomePage(Model model) {
        model.addAttribute("searchParams", Map.of(
            "tripType", "one-way",
            "from", "",
            "to", "",
            "departureDate", "",
            "returnDate", ""
        ));
        return "pages/index";
    }

@GetMapping("/api/airports")
@ResponseBody
public List<String> getAllAirports() {
    List<String> departureAirports = flightRepository.findDistinctFromAirports();
    List<String> arrivalAirports = flightRepository.findDistinctToAirports();
    
    // Combine and remove duplicates
    return Stream.concat(departureAirports.stream(), arrivalAirports.stream())
            .distinct()
            .sorted()
            .toList();
}

@PostMapping("/flights/search")
public String searchFlights(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam String departureDate,
        @RequestParam(required = false) String returnDate,
        @RequestParam String tripType,
        Model model) {

    try {
        // 1. Parse dates with validation
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Strict date parsing
        
        Date depDate = dateFormat.parse(departureDate);
        
        // 2. Standardize airport codes (uppercase and trim)
        String fromAirport = from.trim().toUpperCase();
        String toAirport = to.trim().toUpperCase();

        // 3. Get all distinct airports for the dropdown
        List<String> airports = Stream.concat(
                flightRepository.findDistinctFromAirports().stream(),
                flightRepository.findDistinctToAirports().stream())
                .distinct()
                .sorted()
                .toList();

        // 4. Search for flights
        List<Flight> departingFlights = flightRepository
                .findByFromAirportAndToAirportAndDepartureDate(
                    fromAirport, 
                    toAirport, 
                    depDate);

        // 5. Handle return flights if round-trip
        List<Flight> returningFlights = null;
        if ("round-trip".equalsIgnoreCase(tripType) && returnDate != null && !returnDate.isEmpty()) {
            Date retDate = dateFormat.parse(returnDate);
            returningFlights = flightRepository
                    .findByFromAirportAndToAirportAndDepartureDate(
                            toAirport, 
                            fromAirport, 
                            retDate);
        }

        // 6. Prepare model attributes
        model.addAttribute("departingFlights", departingFlights != null ? departingFlights : Collections.emptyList());
        model.addAttribute("returningFlights", returningFlights != null ? returningFlights : Collections.emptyList());
        model.addAttribute("airports", airports);
        
        model.addAttribute("searchParams", Map.of(
            "from", from,
            "to", to,
            "departureDate", departureDate,
            "returnDate", returnDate != null ? returnDate : "",
            "tripType", tripType,
            "fromCity", from,
            "toCity", to
        ));

    } catch (ParseException e) {
        model.addAttribute("error", "Invalid date format. Please use YYYY-MM-DD format.");
    } catch (Exception e) {
        model.addAttribute("error", "Error searching flights: " + e.getMessage());
        e.printStackTrace(); // Log the error for debugging
    }

    return "pages/flight-search";
}
}
