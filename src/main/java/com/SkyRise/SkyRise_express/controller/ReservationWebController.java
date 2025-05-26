package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.model.FlightSeat;
import com.SkyRise.SkyRise_express.model.Passenger;
import com.SkyRise.SkyRise_express.model.Reservation;
import com.SkyRise.SkyRise_express.service.FlightInstanceService;
import com.SkyRise.SkyRise_express.service.FlightSeatService;
import com.SkyRise.SkyRise_express.service.PassengerService;
import com.SkyRise.SkyRise_express.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class ReservationWebController {

    private final ReservationService reservationService;
    private final FlightInstanceService flightInstanceService;
    private final FlightSeatService flightSeatService;
    private final PassengerService passengerService;

    @Autowired
    public ReservationWebController(ReservationService reservationService,
                                      FlightInstanceService flightInstanceService,
                                      FlightSeatService flightSeatService,
                                      PassengerService passengerService) {
        this.reservationService = reservationService;
        this.flightInstanceService = flightInstanceService;
        this.flightSeatService = flightSeatService;
        this.passengerService = passengerService;
    }

    @PostMapping
    public String createReservation(@RequestParam("flightInstanceId") Long flightInstanceId,
                                    @RequestParam("selectedFlightSeatId") Long selectedFlightSeatId,
                                    @RequestParam("passengerName") String passengerName,
                                    RedirectAttributes redirectAttributes) {

        Optional<FlightInstance> flightInstanceOptional = flightInstanceService.getFlightInstanceByIdWithSeats(flightInstanceId);
        Optional<FlightSeat> flightSeatOptional = flightSeatService.getFlightSeatById(selectedFlightSeatId);

        if (flightInstanceOptional.isEmpty() || flightSeatOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Invalid flight or seat selected.");
            return "redirect:/flights/search"; // Redirect back to search or an error page
        }

        FlightInstance flightInstance = flightInstanceOptional.get();
        FlightSeat flightSeat = flightSeatOptional.get();

        // Check if the seat is already reserved
        if (flightSeat.getReservation() != null) {
             redirectAttributes.addFlashAttribute("error", "Seat is already reserved.");
             return "redirect:/flights/" + flightInstanceId; // Redirect back to flight details
        }

        // Create a new Passenger (for simplicity; in a real app, link to existing user/passenger)
        Passenger passenger = new Passenger();
        passenger.setName(passengerName); // Corrected setter
        // Set other passenger properties if available from the form
        Passenger savedPassenger = passengerService.createPassenger(passenger);

        // Create the Reservation
        Reservation reservation = new Reservation();
        reservation.setFlightInstance(flightInstance);
        reservation.setFlightSeat(flightSeat);
        reservation.setPassenger(savedPassenger);
        // Set other reservation properties (e.g., status, booking date)
        Reservation savedReservation = reservationService.createReservation(reservation);

        // Update the FlightSeat to link it to the reservation
        flightSeat.setReservation(savedReservation);
        flightSeatService.updateFlightSeat(flightSeat);

        // Redirect to a confirmation page (we'll create this next)
        redirectAttributes.addFlashAttribute("success", "Reservation successful!");
        return "redirect:/reservations/" + savedReservation.getReservationNumber(); // Assuming Reservation has getReservationNumber for ID
    }

    @GetMapping("/{reservationNumber}")
    public String showReservationDetails(@PathVariable("reservationNumber") Long reservationNumber, Model model) {
        Optional<Reservation> reservationOptional = reservationService.getReservationById(reservationNumber);
        if (reservationOptional.isPresent()) {
            model.addAttribute("reservation", reservationOptional.get());
            return "pages/confirmation"; // refers to src/main/resources/templates/pages/confirmation.html
        } else {
            // Handle reservation not found
            return "redirect:/"; // Redirect to home or an error page
        }
    }

    @GetMapping
    public String showMyReservations(Model model) {
        // For now, fetch all reservations. In a real app, filter by logged-in user.
        List<Reservation> reservations = reservationService.getAllReservations(); // Assuming ReservationService has this method
        model.addAttribute("reservations", reservations);
        return "pages/myreservations"; // refers to src/main/resources/templates/pages/myreservations.html
    }

    @PostMapping("/cancel/{reservationNumber}")
    public String cancelReservation(@PathVariable("reservationNumber") Long reservationNumber, RedirectAttributes redirectAttributes) {
        boolean isCancelled = reservationService.cancelReservation(reservationNumber); // Assuming ReservationService has this method

        if (isCancelled) {
            redirectAttributes.addFlashAttribute("success", "Reservation " + reservationNumber + " cancelled successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel reservation " + reservationNumber + ".");
        }

        return "redirect:/reservations"; // Redirect back to the reservations list
    }
} 