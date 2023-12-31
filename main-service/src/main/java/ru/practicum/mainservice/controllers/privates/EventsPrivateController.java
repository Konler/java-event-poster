package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.event.*;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.services.EventsService;
import ru.practicum.mainservice.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsPrivateController {
    private final EventsService eventsService;
    private final UserService userService;

    @GetMapping
    List<EventShortDto> getEvent(@PathVariable Integer userId,
                                 @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Получены события, добавленных текущим пользователем: {}", userId);
        return userService.getEvent(userId, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    EventFullDto postEvent(@PathVariable Integer userId,
                           @RequestBody @Valid NewEventDto newEventDto) {
        log.info("Добавление нового события: {}", newEventDto);
        return userService.postEvent(userId, newEventDto);

    }

    @GetMapping("/{eventId}")

    EventFullDto getFullInfoAboutEventById(@PathVariable Integer userId,
                                           @PathVariable Integer eventId) {
        log.info("Запрос на получение полной информации о событии добавленном текущим пользователем");
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
    List<ParticipationRequestDto> getInfoAboutRequestsForParticipationCurrentUser(@PathVariable Integer userId,
                                                                                  @PathVariable Integer eventId) {
        log.info("Получение информации о запросах на участие в событии текущего пользователя");
        List<ParticipationRequestDto> list = userService.getInfoAboutRequestsForParticipationCurrentUser(userId, eventId);
        return list;
    }

    @PatchMapping("/{eventId}/requests")
    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(@PathVariable Integer userId,
                                                                    @PathVariable Integer eventId,
                                                                    @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        log.info("Изменение статуса заявок на участие в событии текущего пользователя");
        return eventsService.updateStatusOfParticipationEvent(userId, eventId, eventRequestStatusUpdateRequest);
    }
}
