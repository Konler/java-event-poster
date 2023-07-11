package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.CompilationDto;
import ru.practicum.mainservice.dto.NewCompilationDto;
import ru.practicum.mainservice.model.UpdateCompilationRequest;
import ru.practicum.mainservice.services.admins.CompilationEventsAdminService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class CompilationEventsAdminController {
    private final CompilationEventsAdminService compilationEventsAdminService;

    @PostMapping
    CompilationDto addNewCompilations(@RequestBody @Valid NewCompilationDto newCompilationDto) {

        log.info("Добавление новой подборки");
        return compilationEventsAdminService.addNewCompilations(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    void deleteUser(@PathVariable Integer compId) {
        log.info("Удаление подборки");
        compilationEventsAdminService.deleteUser(compId);
    }

    @PatchMapping("/{compId}")
    CompilationDto updateCompilation(@PathVariable Integer compId,
                                     @RequestBody @Valid UpdateCompilationRequest updateCompilationRequest) {
        log.info("Обновить информацию о подборке");
        return compilationEventsAdminService.updateCompilation(compId, updateCompilationRequest);
    }

}
