package com.example.CRUD.controller;

import com.example.CRUD.entity.Events;
import com.example.CRUD.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EventController {

    private final EventService eventService;

    @PostMapping("/events")
    public Events postEvents(@RequestBody Events events){
        return eventService.postEvents(events);
    }

    @GetMapping("/events")
    public List<Events> getAllEvents(){
        return eventService.getAllEvents();
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        try{
            eventService.deleteEvent(id);
            return new ResponseEntity<>("Event with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        Events events = eventService.getEventsById(id);
        if(events == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(events);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<?> updateEvents(@PathVariable Long id, @RequestBody Events events){
        Events updatedEvents = eventService.updateEvents(id,events);

        if(updatedEvents == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedEvents);
    }
}
