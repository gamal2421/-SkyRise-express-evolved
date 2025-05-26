package com.SkyRise.SkyRise_express.service;

import com.SkyRise.SkyRise_express.model.Itinerary;

import java.util.List;
import java.util.Optional;

public interface ItineraryService {

    Itinerary createItinerary(Itinerary itinerary);

    Optional<Itinerary> getItineraryById(Long id);

    List<Itinerary> getAllItineraries();

    Itinerary updateItinerary(Itinerary itinerary);

    void deleteItinerary(Long id);

    // Add other relevant methods based on your business logic
} 