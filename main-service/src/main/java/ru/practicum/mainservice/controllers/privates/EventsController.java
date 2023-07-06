package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsController {
    @GetMapping
    List<EventShortDto> getEvent(@PathVariable Integer userId,
                                 @RequestParam(name = "from", required = false) Integer from,
                                 @RequestParam(name = "size", required = false) Integer size) {
        return null;
    }

    @PostMapping
    EventFullDto postEvent(@PathVariable Integer userId,
                           @RequestBody NewEventDto newEventDto) {
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
