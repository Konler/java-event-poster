package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.ParticipationRequestDto;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {
//    private final RequestRepository requestRepository;
    @Override
    public List<ParticipationRequestDto> getInfoAboutPartisipationInAlienEvent(Integer userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto cancelOfRequestForParticipate(Integer userId, Integer requestsId) {
        return null;
    }
}
