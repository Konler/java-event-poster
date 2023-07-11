package ru.practicum.mainservice.services.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.EventSort;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.repositories.EventRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventsPublicServiceImpl implements EventsPublicService{
    private final EventRepository eventRepository;
    @Override
    public List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto getAllInfoAboutEventById(Integer id) {
        return null;
    }
}
