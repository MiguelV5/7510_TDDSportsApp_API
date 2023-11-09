package com.TddSportsApp.controller;

import com.TddSportsApp.controller.dto.CreateEventDto;
import com.TddSportsApp.controller.dto.CreateUserDto;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        EventSearchCriteria eventSearchCriteria = new EventSearchCriteria();
        eventSearchCriteria.setName(name);
        eventSearchCriteria.setLocation(location);
        eventSearchCriteria.setCategory(category);
        eventSearchCriteria.setDistance(distance);
        eventSearchCriteria.setEdition(edition);

        if (startDate != null){
            try {
                Date startDateSetter = formatter.parse(startDate);
                eventSearchCriteria.setStartDate(startDateSetter);
                System.out.println("Start date: " + startDateSetter);
            } catch (ParseException e) {
                System.out.println("Error: " + e);
            }
        } else {
            eventSearchCriteria.setStartDate(null);
        }
        if (endDate != null){
            try {
                Date endDateSetter = formatter.parse(endDate);
                eventSearchCriteria.setEndDate(endDateSetter);
            } catch (ParseException e) {
                System.out.println("Error: " + e);
            }
        } else {
            eventSearchCriteria.setEndDate(null);
        }

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
