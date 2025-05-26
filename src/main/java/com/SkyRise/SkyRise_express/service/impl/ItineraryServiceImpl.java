package com.SkyRise.SkyRise_express.service.impl;

import com.SkyRise.SkyRise_express.model.Itinerary;
import com.SkyRise.SkyRise_express.repository.ItineraryRepository;
import com.SkyRise.SkyRise_express.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;

    @Autowired
    public ItineraryServiceImpl(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    @Override
    public Itinerary createItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    @Override
    public Optional<Itinerary> getItineraryById(Long id) {
        return itineraryRepository.findById(id);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    @Override
    public Itinerary updateItinerary(Itinerary itinerary) {
        // You might want to add logic here to check if the itinerary exists before updating
        return itineraryRepository.save(itinerary);
    }

    @Override
    public void deleteItinerary(Long id) {
        itineraryRepository.deleteById(id);
    }

    // Implement other relevant methods as needed
} 