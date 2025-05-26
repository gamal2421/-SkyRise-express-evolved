package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flights")
public class FlightWebController {

    private final FlightService flightService;

    @Autowired
    public FlightWebController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        return "pages/search";
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam("departureDate") String departureDate,
                                @RequestParam("origin") String origin,
                                @RequestParam("destination") String destination,
                                Model model) {
        List<FlightInstance> flightInstances = flightService.searchFlights(LocalDate.parse(departureDate), origin, destination);
        model.addAttribute("flightInstances", flightInstances);
        return "pages/results";
    }

    @GetMapping("/{id}")
    public String showFlightDetails(@PathVariable("id") Long id, Model model) {
        Optional<FlightInstance> flightInstanceOptional = flightService.getFlightInstanceByIdWithSeats(id);
        if (flightInstanceOptional.isPresent()) {
            model.addAttribute("flightInstance", flightInstanceOptional.get());
            return "pages/details";
        } else {
            // Handle flight instance not found
            return "redirect:/flights/search";
        }
    }
} 