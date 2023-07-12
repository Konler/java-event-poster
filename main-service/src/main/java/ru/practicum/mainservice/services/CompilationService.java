package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    CompilationDto addNewCompilations(NewCompilationDto newCompilationDto);

    void deleteUser(Integer compId);

    CompilationDto updateCompilation(Integer compId, UpdateCompilationRequest updateCompilationRequest);

    public List<CompilationDto> getEvents(Boolean pinned, Integer from, Integer size);
    public CompilationDto getCompilationById( Integer compId);
}
