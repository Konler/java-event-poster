package ru.practicum.mainservice.services.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.CompilationDto;
import ru.practicum.mainservice.dto.NewCompilationDto;
import ru.practicum.mainservice.model.UpdateCompilationRequest;
import ru.practicum.mainservice.repositories.CompilationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationEventsAdminServiceImpl implements CompilationEventsAdminService {
    private final CompilationRepository compilationRepository;
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
}
