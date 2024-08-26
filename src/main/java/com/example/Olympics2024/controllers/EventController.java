package com.example.Olympics2024.controllers;


import com.example.Olympics2024.Models.Athlete;

import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.Models.Event;

import com.example.Olympics2024.dto.AllEvents;
import com.example.Olympics2024.dto.EventRequest;
import com.example.Olympics2024.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<List<AllEvents>> getAllEvents(){
        List<AllEvents> events=eventService.getAllEvents();
        return new ResponseEntity<>(events,HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<String> setEvents(@RequestBody EventRequest eventRequest){
        if(eventRequest.getEvents()<1)
            return new ResponseEntity<>("Need at least one event",HttpStatus.BAD_REQUEST);
        if(eventRequest.getParticipants()<3)
            return new ResponseEntity<>("Need at least 3 participants",HttpStatus.BAD_REQUEST);
        if(eventRequest.getAthletes()<3)
            return new ResponseEntity<>("Need at least 3 athletes",HttpStatus.BAD_REQUEST);
        eventService.startNewOlympics(eventRequest.getEvents(),eventRequest.getAthletes()
                ,eventRequest.getParticipants(),eventRequest.getCountry());

        return new ResponseEntity<>("Events added",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event = eventService.getEventById(id);
        if(event==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(event,HttpStatus.OK);
    }

    @GetMapping("/{id}/toppers")
    public ResponseEntity<List<Country>> getEventToppers(@PathVariable Long id){
        return new ResponseEntity<>(eventService.getTopCountriesByEvent(id),HttpStatus.OK);
    }

    @GetMapping("/simulate/{id}")
    public ResponseEntity<Event> simulateEvent(@PathVariable Long id){
        Optional<Event> event=eventService.simulateEvent(id);
        if(event.isPresent()){
            return new ResponseEntity<>(event.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/simulate")
    public List<Event> simulateAllEvents(){
        return eventService.simulateAllEvents();
    }


}
