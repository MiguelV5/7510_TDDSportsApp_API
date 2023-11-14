package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateEventDto;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.models.*;
import com.TddSportsApp.repositories.EventCriteriaRepository;
import com.TddSportsApp.repositories.EventRepository;
import com.TddSportsApp.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    private EventCriteriaRepository eventCriteriaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private CommentService commentService;

    public EventService(EventRepository eventRepository, EventCriteriaRepository eventCriteriaRepository) {
        this.eventRepository = eventRepository;
        this.eventCriteriaRepository = eventCriteriaRepository;
    }

    public Event createEvent(CreateEventDto eventDto){
        Event event = Event.builder()
                .name(eventDto.getName())
                .location(eventDto.getLocation())
                .category(eventDto.getCategory())
                .edition(eventDto.getEdition())
                .distance(eventDto.getDistance())
                .date(eventDto.getDate())
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

    public List<Event> getEvents(EventSearchCriteria eventSearchCriteria){
        return eventCriteriaRepository.findAllWithFilters(eventSearchCriteria, userService.getLoggedUser().getId());
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

    public void enrollUser(Long eventId) {
        Event event = this.getEventById(eventId);
        UserEntity user = userService.getLoggedUser();

        Inscription inscription = inscriptionService.createInscription(event, user);
    }

    public void createEventComment(Long eventId, String commentText) {
        Event event = this.getEventById(eventId);
        UserEntity user = userService.getLoggedUser();

        Comment comment = commentService.createComment(event, user, commentText);
    }
}
