package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsController {
    @GetMapping
    List<EventShortDto> getEvent(@PathVariable Integer userId,
                                 @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                 @RequestParam(name = "size", required = false,defaultValue = "10") Integer size) {
        return null;
    }

    @PostMapping
    EventFullDto postEvent(@PathVariable Integer userId,
                           @RequestBody NewEventDto newEventDto) {
        log.info("Добавление нового события: {}",newEventDto);
        return null;
    }

    @GetMapping("/{eventId}")
    EventFullDto getFullInfoAboutEventById(@PathVariable Integer userId,
                                           @PathVariable Integer eventId) {
        return null;
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventAddedByCurrentUser(@PathVariable Integer userId,
                                               @PathVariable Integer eventId,
                                               @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @GetMapping("/{eventId}/requests")
    ParticipationRequestDto getInfoAboutRequestsForParticipationCurrentUser(@PathVariable Integer userId,
                                                                            @PathVariable Integer eventId) {
        return null;
    }

    @PatchMapping("/{eventId}/requests")
    EventRequestStatusUpdateResult updateStatusOfParticipationEvent(@PathVariable Integer userId,
                                                                    @PathVariable Integer eventId,
                                                                    @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }
}
