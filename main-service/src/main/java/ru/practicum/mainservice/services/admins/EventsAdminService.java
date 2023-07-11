package ru.practicum.mainservice.services.admins;

import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.model.UpdateEventAdminRequest;

import java.util.List;

public interface EventsAdminService {
    EventFullDto searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, String rangeStart, String rangeEnd, Integer from, Integer size);

    EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
