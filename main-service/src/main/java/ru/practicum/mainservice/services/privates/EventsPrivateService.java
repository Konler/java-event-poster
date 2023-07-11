package ru.practicum.mainservice.services.privates;

import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.dto.NewEventDto;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.model.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.model.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.model.UpdateEventUserRequest;

import java.util.List;

public interface EventsPrivateService {
    List<EventShortDto> getEvent(Integer userId, Integer from, Integer size);

    EventFullDto postEvent(Integer userId, NewEventDto newEventDto);

    EventFullDto getFullInfoAboutEventById(Integer userId, Integer eventId);

    EventFullDto updateEventAddedByCurrentUser(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId);

    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(Integer userId, Integer eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

}
