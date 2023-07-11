package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.dto.NewEventDto;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.model.EventRequestStatusUpdateRequest;
import ru.practicum.mainservice.model.EventRequestStatusUpdateResult;
import ru.practicum.mainservice.model.UpdateEventUserRequest;
import ru.practicum.mainservice.services.privates.EventsPrivateService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsPrivateController {
    private final EventsPrivateService eventsPrivateService;

    @GetMapping
    List<EventShortDto> getEvent(@PathVariable Integer userId,
                                 @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                 @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Получены события, добавленных текущим пользователем: {}", userId);
        return eventsPrivateService.getEvent(userId, from, size);
    }

    @PostMapping
    EventFullDto postEvent(@PathVariable Integer userId,
                           @RequestBody @Valid NewEventDto newEventDto) {
        log.info("Добавление нового события: {}", newEventDto);
        return eventsPrivateService.postEvent(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    EventFullDto getFullInfoAboutEventById(@PathVariable Integer userId,
                                           @PathVariable Integer eventId) {
        log.info("Получена полная информация о событии добавленном текущим пользователем");
        return eventsPrivateService.getFullInfoAboutEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventAddedByCurrentUser(@PathVariable Integer userId,
                                               @PathVariable Integer eventId,
                                               @RequestBody @Valid UpdateEventUserRequest updateEventUserRequest) {
        log.info("Изменение события добавленного текущим пользователем");
        return eventsPrivateService.updateEventAddedByCurrentUser(userId, eventId, updateEventUserRequest);
    }

    @GetMapping("/{eventId}/requests")
    ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(@PathVariable Integer userId,
                                                                            @PathVariable Integer eventId) {
        log.info("Получение информации о запросах на участие в событии текущего пользователя");
        return eventsPrivateService.getInfoAboutRequestsForParticipationCurrentUser(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(@PathVariable Integer userId,
                                                                    @PathVariable Integer eventId,
                                                                    @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        log.info("Изменение статуса заявок на участие в событии текущего пользователя");
        return eventsPrivateService.updateStatusOfParticipationEvent(userId, eventId, eventRequestStatusUpdateRequest);
    }
}
