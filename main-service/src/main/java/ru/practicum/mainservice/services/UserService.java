package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.event.EventFullDto;
import ru.practicum.mainservice.dto.event.EventShortDto;
import ru.practicum.mainservice.dto.event.NewEventDto;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<UserDto> getInfoAboutUser(List<Integer> ids, Integer from, Integer size);

    UserDto addNewUser(NewUserRequest newUserRequest);

    void deleteUser(Integer userId);

    User findUserById(Integer id);

    //фигня
    List<ParticipationRequestDto> getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId);

    EventFullDto postEvent(Integer userId, NewEventDto newEventDto);

    ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId);

    List<EventFullDto> searchOfEvents(List<Integer> uses, List<String> states, List<Integer> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size);

    List<EventShortDto> getEvent(Integer userId, Integer from, Integer size);
}
