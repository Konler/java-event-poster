package ru.practicum.mainservice.services.privates;

import ru.practicum.mainservice.dto.ParticipationRequestDto;

import java.util.List;

public interface ParticipationRequestPrivateService {
    List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(Integer userId);

    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId);

    ParticipationRequestDto cancelOfRequestForParticipate(Integer userId, Integer requestsId);
}
