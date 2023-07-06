package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.EventFullDto;
import ru.practicum.mainservice.model.EventShortDto;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsController {
    @GetMapping
    EventShortDto getEventsWithFiltr(@RequestParam("text") String text,
                                     @RequestParam("categories") List<Integer> categories,
                                     @RequestParam("paid") Boolean paid,
                                     @RequestParam("rangeStart") String rangeStart,
                                     @RequestParam("rangeEnd") String rangeEnd,
                                     @RequestParam("onlyAvailable") Boolean onlyAvailable,
                                     @RequestParam("sort") String sort,
                                     @RequestParam("from") Integer from,
                                     @RequestParam("size") Integer size) {
        return null;
    }

    @GetMapping("/{id}")
    EventFullDto getAllInfoAboutEventById(@PathVariable Integer id) {
        return null;
    }
}
