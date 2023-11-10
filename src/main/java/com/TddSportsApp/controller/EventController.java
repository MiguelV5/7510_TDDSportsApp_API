package com.TddSportsApp.controller;

import com.TddSportsApp.controller.dto.CreateEventDto;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    private Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @PostMapping("")
    public Event createEvent(@Valid @RequestBody CreateEventDto eventDto){
        return eventService.createEvent(eventDto);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @GetMapping("")
    public List<Event> getEvents(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String location,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) Integer distance,
                                 @RequestParam(required = false) Integer edition,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate){

        EventSearchCriteria eventSearchCriteria = EventSearchCriteria.builder()
                .name(name)
                .location(location)
                .category(category)
                .distance(distance)
                .edition(edition)
                .startDate(parseDate(startDate))
                .endDate(parseDate(endDate))
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
}
