package com.example.University_Event_Management.controller;

import com.example.University_Event_Management.exception.ResourceNotFoundException;
import com.example.University_Event_Management.model.Event;
import com.example.University_Event_Management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("")
    public Event addEvent(@RequestBody @Validated Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable int eventId,
            @RequestBody @Validated Event event) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
        existingEvent.setEventName(event.getEventName());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setDate(event.getDate());
        return ResponseEntity.ok(eventRepository.save(existingEvent));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
        eventRepository.delete(event);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/date")
    public List<Event> getAllEventsByDate(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime date) {
        return eventRepository.findByDate(date);
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
    }
}