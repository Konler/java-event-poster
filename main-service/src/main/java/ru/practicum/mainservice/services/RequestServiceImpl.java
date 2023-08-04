package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.enums.StatusRequest;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.mappers.RequestMapper;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.Request;
import ru.practicum.mainservice.model.User;
import ru.practicum.mainservice.repositories.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;


    @Override
    public List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(Integer userId) {

        return requestRepository.findAllByRequester(userId).stream().map(RequestMapper::toRequestDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Request> findRequestByRequester(Integer integer) {
        return requestRepository.findRequestByRequester(integer);
    }

    @Override
    public ParticipationRequestDto createRequest(User user, Event event) {
        Request request = new Request();
        if (event.getRequestModeration() && event.getParticipantLimit() != 0) {
            request.setStatus(StatusRequest.PENDING);
        } else {
            request.setStatus(StatusRequest.CONFIRMED);
        }
        request.setRequester(user.getId());
        request.setEvent(event.getId());
        request.setCreated(LocalDateTime.now());
        request = requestRepository.save(request);
        return RequestMapper.toRequestDto(request);
    }

    @Override
    public ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId) {
        // Request request= requestRepository.findRequestByRequester(userId).orElseThrow(() -> new NotFoundException("Пользователь не оставлял запрос на участие"));


        return RequestMapper.toRequestDto(requestRepository.save(Request.builder()
                .event(eventId)
                .requester(userId)
                .created(LocalDateTime.now())
                .status(StatusRequest.PENDING)
                .build()));


    }

    @Override
    public ParticipationRequestDto cancelOfRequestForParticipate(Integer userId, Integer requestId) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Запрос на участие с id" + requestId + "не найден"));
        if (userId.equals(request.getRequester())) {
            request.setStatus(StatusRequest.CANCELED);
            return RequestMapper.toRequestDto(requestRepository.save(request));
        } else {
            throw new NotFoundException("Пользователь с таким id" + requestId + " не может отменить данный запрос");
        }
    }

    @Override
    public List<Request> findAllByEvent(Integer eventId) {
        return requestRepository.findAllByEvent(eventId);
    }

    @Override
    public List<Request> findAllByIds(List<Integer> eventIds) {
        return requestRepository.findAllById(eventIds);
    }

    @Override
    public List<Request> saveAll(List<Request> ids) {
        return requestRepository.saveAll(ids);
    }

    @Override
    public Integer countConfirmedRequest(Integer idEvent) {
        return requestRepository.countByEventAndStatus(idEvent, StatusRequest.CONFIRMED);
    }

    @Override
    public Request validateParticipateOfUser(Integer userId, Integer eventId) {
        return requestRepository.findByEventAndRequester(eventId, userId);
    }

    @Override
    public Request findByEventAndRequester(Integer eventId, Integer userId) {
        return requestRepository.findByEventAndRequester(eventId, userId);
    }

    public Request findRequestById(Integer id) {
        return requestRepository.findById(id).orElseThrow(() -> new NotFoundException("не найден пользоатель"));
    }
}
