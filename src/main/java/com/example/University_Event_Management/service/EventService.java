package com.example.University_Event_Management.service;

import com.example.University_Event_Management.exception.ResourceNotFoundException;
import com.example.University_Event_Management.model.Event;
import com.example.University_Event_Management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(int eventId, Event event) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
        existingEvent.setEventName(event.getEventName());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setDate(event.getDate());
        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(int eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
        eventRepository.delete(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsByDate(LocalDateTime date) {
        return eventRepository.findByDate(date);
    }

    public Event getEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
    }
}