package com.TddSportsApp.service;

import com.TddSportsApp.models.*;
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

    private Event createMockEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("name");
        event.setDescription("description");
        event.setLocation("location");
        event.setCategory("category");
        event.setDistance(1L);
        event.setEdition(1);
        event.setDate(new Date());
        return event;
    }

    @Test
    public void whenCreateEvent_thenReturnEvent() {
        // given
        CreateEventDto createEventDto = new CreateEventDto();
        createEventDto.setName("name");
        createEventDto.setDescription("description");
        createEventDto.setLocation("location");
        createEventDto.setCategory("category");
        createEventDto.setDistance(20L);
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
        Event event = createMockEvent();

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
    public void whenDeleteEvent_thenDeleteEvent() {
        // given
        Event event = createMockEvent();

        // when
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        eventService.deleteEvent(1L);
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // then
        assertEquals(eventRepository.findById(1L), Optional.empty());
    }

    @Test
    public void givenEvent_whenUpdate_thenReturnUpdatedEvent() {
        // given
        Event event = createMockEvent();

        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setName("updatedName");
        updatedEvent.setDescription("updatedDescription");
        updatedEvent.setLocation("updatedLocation");
        updatedEvent.setCategory("updatedCategory");
        updatedEvent.setDistance(2L);
        updatedEvent.setEdition(2);
        updatedEvent.setDate(new Date());

        // when
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);
        Event foundEvent = eventService.updateEvent(1L, updatedEvent);

        // then
        assertEquals(foundEvent, updatedEvent);
    }

    @Test
    public void givenEvent_whenEnrollUserToEvent_thenInscriptionIsReturned() {
        // given
        Event event = createMockEvent();

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setRole("role");

        Date inscriptionDate = new Date();

        Inscription expectedInscription = new Inscription();
        expectedInscription.setId(new InscriptionKey(event.getId(), user.getId()));
        expectedInscription.setEvent(event);
        expectedInscription.setUser(user);
        expectedInscription.setInscriptionDate(inscriptionDate);

        // when
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        when(userService.getLoggedUser()).thenReturn(user);
        when(inscriptionService.createInscription(any(Event.class), any(UserEntity.class))).thenReturn(Inscription.builder()
                .id(new InscriptionKey(event.getId(), user.getId()))
                .event(event)
                .user(user)
                .inscriptionDate(inscriptionDate)
                .build());

        Inscription inscription = eventService.enrollUser(1L);

        // then
        assertEquals(inscription, expectedInscription);
    }
}
