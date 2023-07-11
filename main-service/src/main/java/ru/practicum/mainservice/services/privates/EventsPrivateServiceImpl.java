package ru.practicum.mainservice.services.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.dto.NewEventDto;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.model.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.model.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.model.UpdateEventUserRequest;
import ru.practicum.mainservice.repositories.EventRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventsPrivateServiceImpl implements EventsPrivateService{
    private final EventRepository eventRepository;
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
}
