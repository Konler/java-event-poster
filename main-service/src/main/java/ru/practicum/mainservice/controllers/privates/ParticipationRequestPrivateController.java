package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
import ru.practicum.mainservice.services.privates.ParticipationRequestPrivateService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class ParticipationRequestPrivateController {
    private final ParticipationRequestPrivateService participationRequestPrivateService;

    @GetMapping
    List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(@PathVariable Integer userId) {
        log.info("Получение информации о заявках текущего пользователя на участие в чужих событиях");
        return participationRequestPrivateService.getInfoAboutPartisipationInAlienEvent(userId);
    }

    @PostMapping
    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(@PathVariable Integer userId,
                                                                         @RequestParam("eventId") Integer eventId) {
        log.info("Добавление запроса от текущего пользователя на участие в событии");
        return participationRequestPrivateService.addRequestOfCurrentUserForParticipateInEvent(userId, eventId);
    }

    @PatchMapping("/{requestsId}/cancel")
    ParticipationRequestDto cancelOfRequestForParticipate(@PathVariable Integer userId,
                                                          @PathVariable Integer requestsId) {
        log.info("Отмена своего запроса на участие в событии");
        return participationRequestPrivateService.cancelOfRequestForParticipate(userId, requestsId);
    }

}
