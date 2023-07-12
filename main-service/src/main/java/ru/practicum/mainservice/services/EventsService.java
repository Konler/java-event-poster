package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.dto.event.*;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.enums.EventSort;

import java.util.List;

public interface EventsService {
    EventFullDto searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, String rangeStart, String rangeEnd, Integer from, Integer size);

    EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<EventShortDto> getEvent(Integer userId, Integer from, Integer size);

    EventFullDto postEvent(Integer userId, NewEventDto newEventDto);

    EventFullDto getFullInfoAboutEventById(Integer userId, Integer eventId);

    EventFullDto updateEventAddedByCurrentUser(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId);

    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(Integer userId, Integer eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);
    List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size);

    EventFullDto getAllInfoAboutEventById(Integer id);
}
