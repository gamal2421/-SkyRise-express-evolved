package com.SkyRise.SkyRise_express.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScheduleAdminController {

    // Will add POST method for assigning crew here

    @PostMapping("/flight-instances/assign-crew")
    public String assignCrew(
            @RequestParam("flightInstanceId") Long flightInstanceId,
            @RequestParam("pilotIds") List<Long> pilotIds,
            @RequestParam("crewIds") List<Long> crewIds,
            @RequestParam("frontDeskOfficerIds") List<Long> frontDeskOfficerIds,
            RedirectAttributes redirectAttributes) {

        // Call a service method to assign the crew to the flight instance
        boolean success = flightInstanceService.assignCrewToFlightInstance(
                flightInstanceId,
                pilotIds,
                crewIds,
                frontDeskOfficerIds
        );

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Crew assigned successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to assign crew.");
        }

        return "redirect:/admin/flight-instances";
    }

    // We will add methods for edit and delete flight instances later

}