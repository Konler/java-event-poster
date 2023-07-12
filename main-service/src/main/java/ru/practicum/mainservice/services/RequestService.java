package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.ParticipationRequestDto;

import java.util.List;

public interface RequestService {
    List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(Integer userId);

    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId);

    ParticipationRequestDto cancelOfRequestForParticipate(Integer userId, Integer requestsId);
}
