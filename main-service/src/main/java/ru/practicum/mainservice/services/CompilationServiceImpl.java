package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationRequest;

import java.util.List;
//import ru.practicum.mainservice.repositories.CompilationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationServiceImpl implements CompilationService {
//    private final CompilationRepository compilationRepository;
    @Override
    public CompilationDto addNewCompilations(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteUser(Integer compId) {

    }

    @Override
    public CompilationDto updateCompilation(Integer compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
    @Override
    public List<CompilationDto> getEvents(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    public CompilationDto getCompilationById(Integer compId) {
        return null;
    }
}
