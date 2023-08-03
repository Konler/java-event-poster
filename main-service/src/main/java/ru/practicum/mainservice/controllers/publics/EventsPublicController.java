package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.ServerClient;
import ru.practicum.mainservice.dto.event.EventFullDto;
import ru.practicum.mainservice.dto.event.EventShortDto;
import ru.practicum.mainservice.enums.EventSort;
import ru.practicum.mainservice.services.EventsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsPublicController {
    private final EventsService eventsService;
    private final ServerClient serverClient;

    @GetMapping////////////////////////////////////////////
    public List<EventShortDto> getEventsWithFiltr(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "categories", required = false) List<Integer> categories,
            @RequestParam(value = "paid", required = false) Boolean paid,
            @RequestParam(value = "rangeStart", required = false) LocalDateTime rangeStart,
            @RequestParam(value = "rangeEnd", required = false) LocalDateTime rangeEnd,
            @RequestParam(value = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(value = "sort", required = false) EventSort sort,
            @RequestParam(value = "from",  defaultValue = "0") Integer from,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            HttpServletRequest request
    ) {
        log.info("Получение событий с возможностью фильтрации");
        serverClient.create(request);
        return eventsService.getEventsWithFiltr(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
        //есть
    EventFullDto getAllInfoAboutEventById(@PathVariable Integer id,
                                          HttpServletRequest httpServletRequest) {
        log.info("Получение подробной информации об опубликованном событии по его id: {}", id);
        serverClient.create(httpServletRequest);
        return eventsService.getAllInfoAboutEventById(id,httpServletRequest);
    }
}

