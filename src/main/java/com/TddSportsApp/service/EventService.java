package com.TddSportsApp.service;

import com.TddSportsApp.models.dto.CommentWithUsernameDto;
import com.TddSportsApp.models.dto.CreateEventDto;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.models.*;
import com.TddSportsApp.models.dto.EventSuperDto;
import com.TddSportsApp.models.dto.ResultWithUsernameDto;
import com.TddSportsApp.repositories.EventCriteriaRepository;
import com.TddSportsApp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
    }

    public List<CommentWithUsernameDto> getEventCommentsWithUsernames(Event event) {
        return event.getComments()
                .stream()
                .map(comment -> new CommentWithUsernameDto(
                        comment.getId(),
                        comment.getCommentText(),
                        comment.getCommentDate(),
                        comment.getUser().getUsername())
                ).toList();
    }

    public List<ResultWithUsernameDto> getEventResultsWithUsernames(Event event) {
        return event.getResults()
                .stream()
                .map(result -> new ResultWithUsernameDto(
                        result.getId(),
                        result.getOfficial(),
                        result.getTime(),
                        result.getPosition(),
                        result.getAcceptedByAthlete(),
                        result.getUser().getUsername())
                ).toList();
    }

    public EventSuperDto getEventByIdWithExtraFields(Long id){
        Event event = this.getEventById(id);

        List<ResultWithUsernameDto> resultsWithUsername = getEventResultsWithUsernames(event);
        List<CommentWithUsernameDto> commentsWithUsername = getEventCommentsWithUsernames(event);

        return new EventSuperDto(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getCategory(),
                event.getDistance(),
                event.getEdition(),
                event.getDate(),
                resultsWithUsername,
                commentsWithUsername,
                event.getInscriptions());
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

    public Inscription enrollUser(Long eventId) {
        Event event = this.getEventById(eventId);
        UserEntity user = userService.getLoggedUser();

        return inscriptionService.createInscription(event, user);
    }

    public void unenrollUser(Long eventId) {
        Event event = this.getEventById(eventId);
        UserEntity user = userService.getLoggedUser();
        inscriptionService.deleteInscription(event, user);
    }
}
