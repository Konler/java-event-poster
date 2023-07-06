package ru.practicum.mainservice.controllers.publics;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.CompilationDto;
import ru.practicum.mainservice.services.MainService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationOfEventsController {
    private final MainService mainService;

    @GetMapping
    public List<CompilationDto> getEvents(@RequestParam(name = "pinned") Boolean pinned,
                                          @RequestParam(name = "from") Integer from,
                                          @RequestParam(name = "size") Integer size) {
        return null;

    }

    @GetMapping("/{compId}")
    public CompilationOfEventsController getCompilationById(@PathVariable Integer compId) {
        return null;
    }
}
