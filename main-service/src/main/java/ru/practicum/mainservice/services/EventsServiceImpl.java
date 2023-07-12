package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.dto.event.*;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.enums.EventSort;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventsServiceImpl implements EventsService{
//    private final EventRepository eventRepository;
    @Override
    public EventFullDto searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
    @Override
    public List<EventShortDto> getEvent(Integer userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto postEvent(Integer userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto getFullInfoAboutEventById(Integer userId, Integer eventId) {
        return null;
    }

    @Override
    public EventFullDto updateEventAddedByCurrentUser(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @Override
    public ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult updateStatusOfParticipationEvent(Integer userId, Integer eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }
    @Override
    public List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto getAllInfoAboutEventById(Integer id) {
        return null;
    }
}
