package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.Request;
import ru.practicum.mainservice.model.User;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(Integer userId);

    Optional<Request> findRequestByRequester(Integer integer);

    ParticipationRequestDto createRequest(User user, Event event);

    Request findRequestById(Integer id);

    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId);

    ParticipationRequestDto cancelOfRequestForParticipate(Integer userId, Integer requestsId);

    List<Request> findAllByEvent(Integer eventId);

    List<Request> findAllByIds(List<Integer> eventIds);

    List<Request> saveAll(List<Request> ids);

    Integer countConfirmedRequest(Integer idEvent);

    Request validateParticipateOfUser(Integer userId, Integer eventId);

    Request findByEventAndRequester(Integer eventId, Integer userId);
}
