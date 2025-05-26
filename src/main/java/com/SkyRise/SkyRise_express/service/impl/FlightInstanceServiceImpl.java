package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.WeeklySchedule;
import com.SkyRise.SkyRise_express.model.CustomSchedule;
import com.SkyRise.SkyRise_express.model.FlightStatus;
import com.SkyRise.SkyRise_express.model.Aircraft;
import com.SkyRise.SkyRise_express.model.Seat;
import com.SkyRise.SkyRise_express.model.FlightSeat;

import com.SkyRise.SkyRise_express.repository.FlightInstanceRepository;
import com.SkyRise.SkyRise_express.service.FlightInstanceService;
import com.SkyRise.SkyRise_express.service.WeeklyScheduleService;
import com.SkyRise.SkyRise_express.service.CustomScheduleService;
import com.SkyRise.SkyRise_express.service.FlightService;
import com.SkyRise.SkyRise_express.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// Use LocalTime from java.time
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Service
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightInstanceRepository flightInstanceRepository;
    private final WeeklyScheduleService weeklyScheduleService;
    private final CustomScheduleService customScheduleService;
    private final FlightService flightService;
    private final SeatService seatService;

    @Autowired
    public FlightInstanceServiceImpl(FlightInstanceRepository flightInstanceRepository,
                                       WeeklyScheduleService weeklyScheduleService,
                                       CustomScheduleService customScheduleService,
                                       FlightService flightService,
                                       SeatService seatService) {
        this.flightInstanceRepository = flightInstanceRepository;
        this.weeklyScheduleService = weeklyScheduleService;
        this.customScheduleService = customScheduleService;
        this.flightService = flightService;
        this.seatService = seatService;
    }

    @Override
    public FlightInstance createFlightInstance(FlightInstance flightInstance) {
        return flightInstanceRepository.save(flightInstance);
    }

    @Override
    public Optional<FlightInstance> getFlightInstanceById(Long id) {
        return flightInstanceRepository.findById(id);
    }

    @Override
    public List<FlightInstance> getAllFlightInstances() {
        return flightInstanceRepository.findAll();
    }

    @Override
    public FlightInstance updateFlightInstance(FlightInstance flightInstance) {
        return flightInstanceRepository.save(flightInstance);
    }

    @Override
    public void deleteFlightInstance(Long id) {
        flightInstanceRepository.deleteById(id);
    }

    @Override
    public Optional<FlightInstance> getFlightInstanceByIdWithSeats(Long id) {
        return flightInstanceRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean generateInstances(Flight flight, String scheduleType, Long weeklyScheduleId, Long customScheduleId, Date startDate, Date endDate) {
        try {
            if ("weekly".equals(scheduleType)) {
                Optional<WeeklySchedule> weeklyScheduleOptional = weeklyScheduleService.getWeeklyScheduleById(weeklyScheduleId);
                if (weeklyScheduleOptional.isEmpty()) {
                    return false;
                }
                WeeklySchedule weeklySchedule = weeklyScheduleOptional.get();
                generateWeeklyInstances(flight, weeklySchedule, startDate, endDate);

            } else if ("custom".equals(scheduleType)) {
                Optional<CustomSchedule> customScheduleOptional = customScheduleService.getCustomScheduleById(customScheduleId);
                if (customScheduleOptional.isEmpty()) {
                    return false;
                }
                CustomSchedule customSchedule = customScheduleOptional.get();
                generateCustomInstances(flight, customSchedule, startDate, endDate);

            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void generateWeeklyInstances(Flight flight, WeeklySchedule weeklySchedule, Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        // Clear time components from endCal to ensure it iterates up to the end of the end date
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);

        while (!startCal.after(endCal)) {
            if (startCal.get(Calendar.DAY_OF_WEEK) == mapDayOfWeek(weeklySchedule.getDayOfWeek())) {
                FlightInstance flightInstance = new FlightInstance();
                flightInstance.setFlight(flight);
                flightInstance.setWeeklySchedule(weeklySchedule);
                flightInstance.setStatus(FlightStatus.Scheduled); // Corrected enum value

                // Combine date from startCal and time from weeklySchedule
                Calendar departureCal = (Calendar) startCal.clone();
                departureCal.set(Calendar.HOUR_OF_DAY, weeklySchedule.getDepartureTime().getHour());
                departureCal.set(Calendar.MINUTE, weeklySchedule.getDepartureTime().getMinute());
                departureCal.set(Calendar.SECOND, weeklySchedule.getDepartureTime().getSecond());

                flightInstance.setDepartureDate(departureCal.getTime()); // Set Date part
                flightInstance.setDepartureTime(weeklySchedule.getDepartureTime()); // Set LocalTime part

                // Calculate arrival date by adding duration to departure date/time
                Calendar arrivalCal = (Calendar) departureCal.clone();
                arrivalCal.add(Calendar.MINUTE, flight.getDurationInMinutes());

                flightInstance.setArrivalDate(arrivalCal.getTime()); // Corrected setter
                // Note: FlightInstance does not have scheduledArrivalTime as LocalTime

                FlightInstance savedFlightInstance = flightInstanceRepository.save(flightInstance);

                // Fetch the Aircraft associated with the FlightInstance after saving (assuming it gets set somehow)
                // A better approach is to associate the Aircraft with FlightInstance during creation
                // but given the current model and generation logic, we fetch it here.
                Optional<FlightInstance> instanceWithAircraft = flightInstanceRepository.findById(savedFlightInstance.getId());
                 if (instanceWithAircraft.isPresent() && instanceWithAircraft.get().getAircraft() != null) {
                     generateFlightSeatsForInstance(savedFlightInstance, instanceWithAircraft.get().getAircraft());
                 } else {
                     // Handle case where aircraft is not associated with the flight instance
                     System.err.println("Warning: Aircraft not associated with generated FlightInstance: " + savedFlightInstance.getId());
                 }
            }
            startCal.add(Calendar.DATE, 1);
        }
    }

    private void generateCustomInstances(Flight flight, CustomSchedule customSchedule, Date startDate, Date endDate) {
        // Check if the custom date is within the specified range
        if (customSchedule.getCustomDate().before(startDate) || customSchedule.getCustomDate().after(endDate)) {
            return;
        }

        FlightInstance flightInstance = new FlightInstance();
        flightInstance.setFlight(flight);
        flightInstance.setCustomSchedule(customSchedule);
        flightInstance.setStatus(FlightStatus.Scheduled); // Corrected enum value

        // For custom schedules, we only have a date. Need a time.
        // Using a simplified approach: combine custom date with a default time (e.g., 12:00 PM)
        Calendar departureCal = Calendar.getInstance();
        departureCal.setTime(customSchedule.getCustomDate());
        departureCal.set(Calendar.HOUR_OF_DAY, 12); // Default time: 12:00 PM
        departureCal.set(Calendar.MINUTE, 0);
        departureCal.set(Calendar.SECOND, 0);

        flightInstance.setDepartureDate(departureCal.getTime()); // Set Date part
        flightInstance.setDepartureTime(LocalTime.of(12, 0)); // Set LocalTime part (default)

        // Calculate arrival date by adding duration to departure date/time
        Calendar arrivalCal = (Calendar) departureCal.clone();
        arrivalCal.add(Calendar.MINUTE, flight.getDurationInMinutes());

        flightInstance.setArrivalDate(arrivalCal.getTime()); // Corrected setter

        FlightInstance savedFlightInstance = flightInstanceRepository.save(flightInstance);

         // Fetch the Aircraft associated with the FlightInstance after saving (assuming it gets set somehow)
         Optional<FlightInstance> instanceWithAircraft = flightInstanceRepository.findById(savedFlightInstance.getId());
          if (instanceWithAircraft.isPresent() && instanceWithAircraft.get().getAircraft() != null) {
              generateFlightSeatsForInstance(savedFlightInstance, instanceWithAircraft.get().getAircraft());
          } else {
              // Handle case where aircraft is not associated with the flight instance
              System.err.println("Warning: Aircraft not associated with generated FlightInstance: " + savedFlightInstance.getId());
          }
    }

    private int mapDayOfWeek(java.time.DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return Calendar.MONDAY;
            case TUESDAY: return Calendar.TUESDAY;
            case WEDNESDAY: return Calendar.WEDNESDAY;
            case THURSDAY: return Calendar.THURSDAY;
            case FRIDAY: return Calendar.FRIDAY;
            case SATURDAY: return Calendar.SATURDAY;
            case SUNDAY: return Calendar.SUNDAY;
            default: throw new IllegalArgumentException("Invalid DayOfWeek: " + dayOfWeek);
        }
    }

    private void generateFlightSeatsForInstance(FlightInstance flightInstance, Aircraft aircraft) {
        if (aircraft != null && aircraft.getTotalSeats() > 0) {
            List<Seat> baseSeats = seatService.getSeatsByAircraftId(aircraft.getId());

            for (Seat baseSeat : baseSeats) {
                FlightSeat flightSeat = new FlightSeat();
                flightSeat.setFlightInstance(flightInstance);
                flightSeat.setSeat(baseSeat);
                flightSeat.setIsAvailable(true); // Corrected property name

                // Copy properties from baseSeat
                flightSeat.setSeatNumber(baseSeat.getSeatNumber()); // Corrected property name
                 if (baseSeat.getSeatType() != null) {
                     flightSeat.setSeatType(baseSeat.getSeatType()); // Corrected property name
                 }

                 // Assume SeatService has a method to save FlightSeats related to a FlightInstance
                 seatService.createFlightSeatForInstance(flightSeat); // Assuming SeatService has this method
            }
        }
    }

}