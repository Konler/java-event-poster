package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.ParticipationRequestDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class RequestsForParticipationController {
    @GetMapping
    ParticipationRequestDto getInfoAboutPartisipationInAlienEvent(@PathVariable Integer userId) {
        return null;
    }

    @PostMapping
    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(@PathVariable Integer userId,
                                                                         @RequestParam("eventId") Integer eventId) {
        return null;
    }

    @PatchMapping("/{requestsId}/cancel")
    ParticipationRequestDto cancelOfRequestForParticipate(@PathVariable Integer userId,
                                                          @PathVariable Integer requestsId) {
        return null;
    }

}
