package ru.practicum.mainservice.services.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.model.UpdateEventAdminRequest;
import ru.practicum.mainservice.repositories.EventRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventsAdminServiceImpl implements EventsAdminService{
    private final EventRepository eventRepository;
    @Override
    public EventFullDto searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
