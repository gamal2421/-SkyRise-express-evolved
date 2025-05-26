package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.FlightInstance;
import com.SkyRise.SkyRise_express.model.Flight;
import com.SkyRise.SkyRise_express.model.WeeklySchedule;
import com.SkyRise.SkyRise_express.model.CustomSchedule;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightInstanceService {

    FlightInstance createFlightInstance(FlightInstance flightInstance);

    Optional<FlightInstance> getFlightInstanceById(Long id);

    List<FlightInstance> getAllFlightInstances();

    FlightInstance updateFlightInstance(FlightInstance flightInstance);

    void deleteFlightInstance(Long id);

    Optional<FlightInstance> getFlightInstanceByIdWithSeats(Long id);

    boolean generateInstances(Flight flight, String scheduleType, Long weeklyScheduleId, Long customScheduleId, Date startDate, Date endDate);

    boolean cancelFlightInstance(Long id);

    boolean assignCrewToFlightInstance(Long flightInstanceId, List<Long> pilotIds, List<Long> crewIds, List<Long> frontDeskOfficerIds);

    // Add other relevant methods based on your business logic
} 