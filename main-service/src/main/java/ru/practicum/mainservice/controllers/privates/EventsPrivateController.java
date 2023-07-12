package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.dto.event.EventFullDto;
import ru.practicum.mainservice.dto.event.EventShortDto;
import ru.practicum.mainservice.dto.event.NewEventDto;
import ru.practicum.mainservice.dto.event.UpdateEventUserRequest;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.services.EventsService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsPrivateController {
    private final EventsService eventsService;

    @GetMapping
    List<EventShortDto> getEvent(@PathVariable Integer userId,
                                 @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                 @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Получены события, добавленных текущим пользователем: {}", userId);
        return eventsService.getEvent(userId, from, size);
    }

    @PostMapping
    EventFullDto postEvent(@PathVariable Integer userId,
                           @RequestBody @Valid NewEventDto newEventDto) {
        log.info("Добавление нового события: {}", newEventDto);
        return eventsService.postEvent(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    EventFullDto getFullInfoAboutEventById(@PathVariable Integer userId,
                                           @PathVariable Integer eventId) {
        log.info("Получена полная информация о событии добавленном текущим пользователем");
        return eventsService.getFullInfoAboutEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventAddedByCurrentUser(@PathVariable Integer userId,
                                               @PathVariable Integer eventId,
                                               @RequestBody @Valid UpdateEventUserRequest updateEventUserRequest) {
        log.info("Изменение события добавленного текущим пользователем");
        return eventsService.updateEventAddedByCurrentUser(userId, eventId, updateEventUserRequest);
    }

    @GetMapping("/{eventId}/requests")
    ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(@PathVariable Integer userId,
                                                                            @PathVariable Integer eventId) {
        log.info("Получение информации о запросах на участие в событии текущего пользователя");
        return eventsService.getInfoAboutRequestsForParticipationCurrentUser(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(@PathVariable Integer userId,
                                                                    @PathVariable Integer eventId,
                                                                    @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        log.info("Изменение статуса заявок на участие в событии текущего пользователя");
        return eventsService.updateStatusOfParticipationEvent(userId, eventId, eventRequestStatusUpdateRequest);
    }
}
