package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.EventFullDto;
import ru.practicum.mainservice.model.UpdateEventAdminRequest;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventsController {
    @GetMapping
    EventFullDto searchOfEvents(@RequestParam("users") List<Integer> uses,
                                @RequestParam("states") List<String> states,
                                @RequestParam("categories") List<Integer> categories,
                                @RequestParam("rangeStart") String rangeStart,
                                @RequestParam("rangeEnd") String rangeEnd,
                                @RequestParam("from") Integer from,
                                @RequestParam("size") Integer size) {
        return null;
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventsAndStatus(@PathVariable Integer eventId,
                                       @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return null;

    }

}
