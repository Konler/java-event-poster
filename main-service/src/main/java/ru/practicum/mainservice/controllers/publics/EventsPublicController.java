package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.EventSort;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.services.publics.EventsPublicService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsPublicController {
    private final EventsPublicService eventsPublicService;

    @GetMapping
    List<EventShortDto> getEventsWithFiltr(@RequestParam(value = "text", required = false) String text,
                                           @RequestParam(value = "categories", required = false) List<Integer> categories,
                                           @RequestParam(value = "paid", required = false) Boolean paid,
                                           @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                           @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                           @RequestParam(value = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
                                           @RequestParam(value = "sort", required = false) EventSort sort,
                                           @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                           @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Получение событий с возможностью фильтрации");
        return eventsPublicService.getEventsWithFiltr(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
    EventFullDto getAllInfoAboutEventById(@PathVariable Integer id) {

        log.info("Получение подробной информации об опубликованном событии по его id: {}", id);
        return eventsPublicService.getAllInfoAboutEventById(id);
    }
}

