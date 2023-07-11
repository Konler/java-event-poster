package ru.practicum.mainservice.services.publics;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.mainservice.dto.CompilationDto;

import java.util.List;

public interface CompilationEventsPublicService {
    public List<CompilationDto> getEvents( Boolean pinned, Integer from, Integer size);
    public CompilationDto getCompilationById( Integer compId);
}
