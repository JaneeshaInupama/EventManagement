package com.example.CRUD.service;

import com.example.CRUD.entity.Events;
import com.example.CRUD.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Events postEvents(Events events){
        return eventRepository.save(events);
    }

    public List<Events> getAllEvents(){
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id){
        if(!eventRepository.existsById(id)){
            throw new EntityNotFoundException("Event with ID " + id + " not found");
        }
        eventRepository.deleteById(id);
    }

    public Events getEventsById(Long id){
        return eventRepository.findById(id).orElse(null);
    }

    public Events updateEvents(Long id, Events events){
        Optional<Events> optionalEvents = eventRepository.findById(id);
        if (optionalEvents.isPresent()){
            Events existingEvents = optionalEvents.get();

            existingEvents.setName(events.getName());
            existingEvents.setLocation(events.getLocation());
            existingEvents.setDate(events.getDate());
            existingEvents.setAmount(events.getAmount());
            existingEvents.setStatus(events.getStatus());

            return eventRepository.save(existingEvents);
        }
        return null;
    }
}
