package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.Aircraft;
import com.SkyRise.SkyRise_express.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/aircraft")
public class AircraftAdminController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftAdminController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public String listAircraft(Model model) {
        List<Aircraft> aircraftList = aircraftService.getAllAircraft(); // Assuming AircraftService has this method
        model.addAttribute("aircraftList", aircraftList);
        return "admin/listAircraft"; // refers to src/main/resources/templates/admin/listAircraft.html
    }

    @GetMapping("/new")
    public String showAddAircraftForm(Model model) {
        model.addAttribute("aircraft", new Aircraft());
        return "admin/addAircraft"; // refers to src/main/resources/templates/admin/addAircraft.html
    }

    @PostMapping("/new")
    public String addAircraft(@ModelAttribute("aircraft") Aircraft aircraft, RedirectAttributes redirectAttributes) {
        aircraftService.createAircraft(aircraft);
        redirectAttributes.addFlashAttribute("success", "Aircraft added successfully!");
        return "redirect:/admin/aircraft"; // Redirect back to the aircraft list
    }

    @GetMapping("/{id}")
    public String showAircraftDetails(@PathVariable("id") Long id, Model model) {
        Optional<Aircraft> aircraftOptional = aircraftService.getAircraftById(id);
        if (aircraftOptional.isPresent()) {
            model.addAttribute("aircraft", aircraftOptional.get());
            return "admin/viewAircraft"; // refers to src/main/resources/templates/admin/viewAircraft.html
        } else {
            // Handle aircraft not found, maybe redirect to list or an error page
            return "redirect:/admin/aircraft";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditAircraftForm(@PathVariable("id") Long id, Model model) {
        Optional<Aircraft> aircraftOptional = aircraftService.getAircraftById(id);
        if (aircraftOptional.isPresent()) {
            model.addAttribute("aircraft", aircraftOptional.get());
            return "admin/addAircraft"; // Reuse the add aircraft form for editing
        } else {
            return "redirect:/admin/aircraft";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateAircraft(@PathVariable("id") Long id, @ModelAttribute("aircraft") Aircraft aircraft, RedirectAttributes redirectAttributes) {
        // Ensure the ID from the path is set on the aircraft object
        aircraft.setId(id);
        aircraftService.updateAircraft(aircraft);
        redirectAttributes.addFlashAttribute("success", "Aircraft updated successfully!");
        return "redirect:/admin/aircraft"; // Redirect back to the aircraft list
    }

    @PostMapping("/delete/{id}")
    public String deleteAircraft(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        aircraftService.deleteAircraft(id);
        redirectAttributes.addFlashAttribute("success", "Aircraft deleted successfully!");
        return "redirect:/admin/aircraft";
    }
} 