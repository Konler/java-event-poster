package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.event.EventFullDto;
import ru.practicum.mainservice.dto.event.UpdateEventAdminRequest;
import ru.practicum.mainservice.services.EventsService;
import ru.practicum.mainservice.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventsAdminController {
    private final EventsService eventsService;
    private final UserService userService;

    @GetMapping
    public List<EventFullDto> searchEvents(@RequestParam(value = "users", required = false) List<Integer> uses,
                                           @RequestParam(value = "states", required = false) List<String> states,
                                           @RequestParam(value = "categories", required = false) List<Integer> categories,
                                           @RequestParam(value = "rangeStart", required = false) LocalDateTime rangeStart,
                                           @RequestParam(value = "rangeEnd", required = false) LocalDateTime rangeEnd,
                                           @RequestParam(value = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                           @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size) {
        log.info("Поиск события");
        return userService.searchOfEvents(uses, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventsAndStatus(@PathVariable Integer eventId,
                                              @RequestBody @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("Редактирование данных события и его статуса");
        return eventsService.updateEventsAndStatus(eventId, updateEventAdminRequest);
    }
}
