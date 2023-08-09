package ru.practicum.mainservice.controllers.publics;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.services.CompilationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationEventsPublicController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getEvents(@RequestParam(name = "pinned", required = false) Boolean pinned,
                                          @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Получение подборок событий");
        return compilationService.getEvents(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Integer compId) {
        log.info("Получеие подборки событий по его id: {}", compId);
        return compilationService.getCompilationById(compId);
    }
}
