package ru.practicum.mainservice.services.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.CompilationDto;
import ru.practicum.mainservice.repositories.CompilationRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationEventsPublicServiceImpl implements CompilationEventsPublicService{
    private final CompilationRepository compilationRepository;
    @Override
    public List<CompilationDto> getEvents(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    public CompilationDto getCompilationById(Integer compId) {
        return null;
    }
}
