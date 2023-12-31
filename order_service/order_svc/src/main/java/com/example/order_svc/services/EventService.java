package com.example.order_svc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_svc.models.entities.Event;
import com.example.order_svc.models.repos.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {

        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEventData(Event event) {
        return eventRepository.save(event);
    }

    // addMultipleEvents
    public List<Event> addMultipleEvents(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    // findByOrderDetailsId
    public List<Event> findByOrderDetailsId(Long orderDetailsId) {
        return eventRepository.findByOrderDetailsIdOrderByDisplayOrder(orderDetailsId);
    }
}
