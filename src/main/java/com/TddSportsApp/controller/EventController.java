package com.TddSportsApp.controller;

import com.TddSportsApp.models.dto.CreateEventDto;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.models.dto.EventSuperDto;
import com.TddSportsApp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public EventSuperDto getEventById(@PathVariable Long id){
        return eventService.getEventByIdWithExtraFields(id);
    }

    @GetMapping("")
    public List<Event> getEvents(@RequestParam(required = false) String location,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) Integer distance,
                                 @RequestParam(required = false) Integer edition,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate,
                                 @RequestParam(required = false) Boolean enrolled){

        EventSearchCriteria eventSearchCriteria = EventSearchCriteria.builder()
                .location(location)
                .category(category)
                .distance(distance)
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
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        return eventService.updateEvent(id, event);
    }

    @PostMapping("/{id}/enroll")
    public void enrollUser(@PathVariable Long id){
        eventService.enrollUser(id);
    }

    @DeleteMapping("/{id}/unenroll")
    public void unenrollUser(@PathVariable Long id){
        eventService.unenrollUser(id);
    }
}
