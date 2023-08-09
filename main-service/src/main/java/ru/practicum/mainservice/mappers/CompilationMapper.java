package ru.practicum.mainservice.mappers;

import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationRequest;
import ru.practicum.mainservice.model.Compilation;

import java.util.Collections;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static Compilation fromNewCompilationDtoToCompilation(NewCompilationDto newCompilationDto) {
        return Compilation.builder()
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .build();
    }

    public static CompilationDto fromCompilationToCompilationDto(Compilation compilation) {

        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(compilation.getEvents() == null ? Collections.emptyList() :
                        compilation.getEvents().stream().map(EventMapper::fromEventToEventShortDto).collect(Collectors.toList())
                )
                .build();
    }

    public static Compilation fromUpdateCompilationRequestToCompilation(UpdateCompilationRequest updateCompilationRequest) {
        return Compilation.builder().pinned(updateCompilationRequest.getPinned())
                .title(updateCompilationRequest.getTitle()).build();
    }


}
