package ru.practicum.mainservice.mappers;

import ru.practicum.mainservice.dto.CompilationDto;
import ru.practicum.mainservice.dto.NewCompilationDto;
import ru.practicum.mainservice.model.UpdateCompilationRequest;
import ru.practicum.mainservice.model.entities.Compilation;

import java.util.Collections;
import java.util.stream.Collectors;

public class CompilationMapper {

    Compilation fromNewCompilationDtoToCompilation(NewCompilationDto newCompilationDto) {
        return Compilation.builder()
                .pinned(newCompilationDto.getPinned()).title(newCompilationDto.getTitle()).
                build();
    }

    CompilationDto fromCompilationToCompilationDto(Compilation compilation) {

        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(compilation.getEvents() == null ? Collections.emptyList() :
                        compilation.getEvents().stream().map(EventMapper::fromEventToEventShortDto).collect(Collectors.toList())
                )
                .build();
    }

    Compilation fromUpdateCompilationRequestToCompilation(UpdateCompilationRequest updateCompilationRequest) {
        return Compilation.builder().pinned(updateCompilationRequest.getPinned())
                .title(updateCompilationRequest.getTitle()).build();
    }
}
