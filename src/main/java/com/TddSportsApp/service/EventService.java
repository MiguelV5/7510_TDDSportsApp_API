package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateEventDto;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(CreateEventDto eventDto){
        Event event = Event.builder()
                .name(eventDto.getName())
                .location(eventDto.getLocation())
                .type(eventDto.getType())
                .edition(eventDto.getEdition())
                .distance(eventDto.getDistance())
                .modality(eventDto.getModality())
                .build();

        eventRepository.save(event);
        return event;
    }

    public Event getEventById(Long id){
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()){
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        return event.get();
    }

    public List<Event> findAll(){
        return (List<Event>) eventRepository.findAll();
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent){
        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()){
            throw new EventNotFoundException("Event not found with id: " + id);
        }

        updatedEvent.setId(id);
        return eventRepository.save(updatedEvent);
    }
}
