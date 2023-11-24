package com.TddSportsApp.controller;

import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.models.Inscription;
import com.TddSportsApp.models.dto.CreateEventDto;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.models.dto.EventSuperDto;
import com.TddSportsApp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.TddSportsApp.utils.DateParser.parseDate;

@RestController()
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("")
    public Event createEvent(@Valid @RequestBody CreateEventDto eventDto){
        return eventService.createEvent(eventDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(eventService.getEventByIdWithExtraFields(id));
        } catch(EventNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public List<Event> getEvents(@RequestParam(required = false) String location,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) Integer startDistance,
                                 @RequestParam(required = false) Integer endDistance,
                                 @RequestParam(required = false) Integer edition,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate,
                                 @RequestParam(required = false) Boolean enrolled){

        EventSearchCriteria eventSearchCriteria = EventSearchCriteria.builder()
                .location(location)
                .category(category)
                .startDistance(startDistance)
                .edition(edition)
                .startDate(parseDate(startDate))
                .endDate(parseDate(endDate))
                .enrolled(enrolled)
                .build();

        return eventService.getEvents(eventSearchCriteria);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event event){
        try {
            return ResponseEntity.ok(eventService.updateEvent(id, event));
        } catch(EventNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<?> enrollUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(eventService.enrollUser(id));
        } catch(EventNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/unenroll")
    public ResponseEntity<?> unenrollUser(@PathVariable Long id){
        try {
            eventService.unenrollUser(id);
            return ResponseEntity.ok().build();
        } catch(EventNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
