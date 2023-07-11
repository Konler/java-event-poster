package ru.practicum.mainservice.services.publics;

import ru.practicum.mainservice.EventSort;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;

import java.util.List;

public interface EventsPublicService {
    List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size);

    EventFullDto getAllInfoAboutEventById(Integer id);
}
