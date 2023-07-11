package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.model.UpdateEventAdminRequest;
import ru.practicum.mainservice.services.admins.EventsAdminService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventsAdminController {
    private final EventsAdminService eventsAdminService;

    @GetMapping
    EventFullDto searchOfEvents(@RequestParam(value = "users", required = false) List<Integer> uses,
                                @RequestParam(value = "states", required = false) List<String> states,
                                @RequestParam(value = "categories", required = false) List<Integer> categories,
                                @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Поиск события");
        return eventsAdminService.searchOfEvents(uses, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventsAndStatus(@PathVariable Integer eventId,
                                       @RequestBody @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("Редактирование данных события и его статуса");
        return eventsAdminService.updateEventsAndStatus(eventId, updateEventAdminRequest);

    }

}
