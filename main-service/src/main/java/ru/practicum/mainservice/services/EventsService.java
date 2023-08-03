package ru.practicum.mainservice.services;

import org.springframework.data.domain.PageRequest;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.dto.event.*;
import ru.practicum.mainservice.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.dto.event.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.enums.EventSort;
import ru.practicum.mainservice.model.Event;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventsService {
   // List<EventFullDto> searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, String rangeStart, String rangeEnd, Integer from, Integer size);

    EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<Event> findAllByIdIn(List<Integer> eventId);

    LocationService getLocationService();
    Event addEvent(Event event);
    CategoriesService getCategoriesService();

    EventFullDto getFullInfoAboutEventById(Integer userId, Integer eventId);

    EventFullDto updateEventAddedByCurrentUser(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    //List<ParticipationRequestDto> getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId);

    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(Integer userId, Integer eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);
    List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size);

    EventFullDto getAllInfoAboutEventById(Integer id, HttpServletRequest httpServletRequest);
 Event getEventById(Integer id);

    List<EventFullDto> getEvents(List<Integer> users, List<String> states, List<Integer> categories, LocalDateTime
            rangeStart, LocalDateTime rangeEnd, Integer from, Integer size);
    List<EventShortDto> getEventsUser(Integer userId, PageRequest pageRequest);
}
