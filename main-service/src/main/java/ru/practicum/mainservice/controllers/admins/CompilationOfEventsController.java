package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.CompilationDto;
import ru.practicum.mainservice.model.NewCompilationDto;
import ru.practicum.mainservice.model.UpdateCompilationRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class CompilationOfEventsController {
    @PostMapping
    CompilationDto addNewCompilations(@RequestBody NewCompilationDto newCompilationDto) {
        return null;
    }

    @DeleteMapping("/{compId}")
    void deleteUser(@PathVariable Integer compId) {

    }

    @PatchMapping("/{compId}")
    CompilationDto updateCompilation(@PathVariable Integer compId,
                                     @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }

}
