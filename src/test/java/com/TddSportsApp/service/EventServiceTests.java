package com.TddSportsApp.service;

import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.CreateEventDto;
import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.repositories.EventCriteriaRepository;
import com.TddSportsApp.repositories.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserService userService;

    @Mock
    private InscriptionService inscriptionService;

    @Mock
    private EventCriteriaRepository eventCriteriaRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void whenCreateEvent_thenReturnEvent() {
        // given
        CreateEventDto createEventDto = new CreateEventDto();
        createEventDto.setName("name");
        createEventDto.setDescription("description");
        createEventDto.setLocation("location");
        createEventDto.setCategory("category");
        createEventDto.setDistance(1L);
        createEventDto.setEdition(1);
        createEventDto.setDate(new Date());

        // when
        when(eventRepository.save(any(Event.class))).thenReturn(new Event());
        Event event = eventService.createEvent(createEventDto);

        // then
        assertThat(event.getName()).isEqualTo(createEventDto.getName());
        assertThat(event.getDescription()).isEqualTo(createEventDto.getDescription());
        assertThat(event.getLocation()).isEqualTo(createEventDto.getLocation());
        assertThat(event.getCategory()).isEqualTo(createEventDto.getCategory());
        assertThat(event.getDistance()).isEqualTo(createEventDto.getDistance());
        assertThat(event.getEdition()).isEqualTo(createEventDto.getEdition());
        assertThat(event.getDate()).isEqualTo(createEventDto.getDate());
    }

    @Test public void whenGetEventById_thenReturnEventWithDetails(){
        // given
        Event event = new Event();
        event.setId(1L);
        event.setName("name");
        event.setDescription("description");
        event.setLocation("location");
        event.setCategory("category");
        event.setDistance(1L);
        event.setEdition(1);
        event.setDate(new Date());

        // when
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        Event foundEvent = eventService.getEventById(1L);

        // then
        assertEquals(foundEvent, event);
    }

    @Test
    public void givenEventWithInvalidId_whenGetEventById_thenThrowEventNotFoundException() {
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        try {
            eventService.getEventById(1L);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Event not found with ID: 1");
        }
    }

    @Test
    public void whenGetAllEventsWithoutFilters_thenAllEventsAreReturned(){
        // given
        Event event1 = new Event();
        event1.setId(1L);
        event1.setName("name1");
        event1.setDescription("description1");
        event1.setLocation("location1");
        event1.setCategory("category1");
        event1.setDistance(1L);
        event1.setEdition(1);
        event1.setDate(new Date());

        Event event2 = new Event();
        event2.setId(2L);
        event2.setName("name2");
        event2.setDescription("description2");
        event2.setLocation("location2");
        event2.setCategory("category2");
        event2.setDistance(2L);
        event2.setEdition(2);
        event2.setDate(new Date());

        // when
        when(userService.getLoggedUser()).thenReturn(new UserEntity());
        when(userService.getLoggedUser().getId()).thenReturn(1L);
        when(eventCriteriaRepository.findAllWithFilters(eq(EventSearchCriteria.builder().build()), any(Long.class))).thenReturn(List.of(event1, event2));
        List<Event> foundEvents = eventService.getEvents(null);

        // then
        assertThat(foundEvents.size()).isEqualTo(2);
        assertEquals(foundEvents.get(0), event1);
        assertEquals(foundEvents.get(1), event2);

    }
}
