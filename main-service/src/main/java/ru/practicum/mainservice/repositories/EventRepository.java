package ru.practicum.mainservice.repositories;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.practicum.mainservice.enums.StateOfEvent;
import ru.practicum.mainservice.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, QuerydslPredicateExecutor<Event> {

    List<Event> findAllByInitiatorId(Integer userId, PageRequest pageRequest);

    List<Event> findByState(StateOfEvent state);

    List<Event> findAllByIdIn(List<Integer> eventId);
}
