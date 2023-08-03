package ru.practicum.mainservice.controllers.privates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.services.RequestService;
import ru.practicum.mainservice.services.UserService;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class ParticipationRequestPrivateController {
    private final RequestService requestService;
    private final UserService userService;

    @GetMapping//есть
    List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(@PathVariable Integer userId) {
        log.info("Получение информации о заявках текущего пользователя на участие в чужих событиях");
        return requestService.getInfoAboutPartisipationInAlienEvent(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//есть
    ParticipationRequestDto addRequest(@PositiveOrZero @PathVariable Integer userId, @PositiveOrZero @RequestParam("eventId") Integer eventId) {
        log.info("Добавление запроса от текущего пользователя на участие в событии");
        ParticipationRequestDto participationRequestDto=userService.addRequestOfCurrentUserForParticipateInEvent(userId, eventId);
        return participationRequestDto;
    }

    @PatchMapping("/{requestsId}/cancel")//есть
    ParticipationRequestDto cancelOfRequestForParticipate(@PathVariable Integer userId,
                                                          @PathVariable Integer requestsId) {
        log.info("Отмена своего запроса на участие в событии");
        return requestService.cancelOfRequestForParticipate(userId, requestsId);
    }

}
