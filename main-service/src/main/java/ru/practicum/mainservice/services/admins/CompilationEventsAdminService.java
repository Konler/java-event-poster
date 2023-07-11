package ru.practicum.mainservice.services.admins;

import ru.practicum.mainservice.dto.CompilationDto;
import ru.practicum.mainservice.dto.NewCompilationDto;
import ru.practicum.mainservice.model.UpdateCompilationRequest;

public interface CompilationEventsAdminService {
    CompilationDto addNewCompilations(NewCompilationDto newCompilationDto);

    void deleteUser(Integer compId);

    CompilationDto updateCompilation(Integer compId, UpdateCompilationRequest updateCompilationRequest);

}
