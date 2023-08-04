package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationRequest;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.mappers.CompilationMapper;
import ru.practicum.mainservice.model.Compilation;
import ru.practicum.mainservice.repositories.CompilationRepository;
import ru.practicum.mainservice.util.General;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventsService eventsService;

    @Override
    public CompilationDto addNewCompilations(NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationRepository.save(CompilationMapper.fromNewCompilationDtoToCompilation(newCompilationDto));
        compilation.setEvents(newCompilationDto.getEvents().stream().map(eventsService::getEventById).collect(Collectors.toList()));
        return CompilationMapper.fromCompilationToCompilationDto(compilation);
    }

    @Override
    public void deleteCompilation(Integer compId) {
        compilationRepository.delete(compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Подоборка не найдена")));
    }

    @Override
    public CompilationDto updateCompilation(Integer compId,
                                            UpdateCompilationRequest updateCompilationRequest) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Подборка не найдена с таким id : {}"));
        if (updateCompilationRequest.getPinned() != null) {
            compilation.setPinned(updateCompilationRequest.getPinned());
        }
        if (updateCompilationRequest.getTitle() != null) {
            compilation.setTitle(updateCompilationRequest.getTitle());
        }
        if (updateCompilationRequest.getEvents() != null) {
            compilation.setEvents(eventsService.findAllByIdIn(updateCompilationRequest.getEvents()));
        }
        log.info("Подборка событий с id {} обновлена", compId);
        return CompilationMapper.fromCompilationToCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    public List<CompilationDto> getEvents(Boolean pinned, Integer from, Integer size) {
        log.info("Получение списка всех подборок событий");
        List<Compilation> compilations;
        PageRequest pageable = General.toPage(from, size, Sort.unsorted());
        if (pinned == null) {
            pinned = false;
        }
        if (pinned) {
            compilations = compilationRepository.findAllByPinned(pinned, pageable);
        } else {
            compilations = compilationRepository.findAll(pageable).getContent();
        }
        return compilations.stream()
                .map(CompilationMapper::fromCompilationToCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Integer compId) {
        return CompilationMapper.fromCompilationToCompilationDto(compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Подоборка не найдена")));
    }
}
