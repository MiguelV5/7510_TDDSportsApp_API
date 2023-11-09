package com.TddSportsApp.repositories;

import com.TddSportsApp.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
