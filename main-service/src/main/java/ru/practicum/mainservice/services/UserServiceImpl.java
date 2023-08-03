package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.event.EventFullDto;
import ru.practicum.mainservice.dto.event.EventShortDto;
import ru.practicum.mainservice.dto.event.NewEventDto;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.enums.StateOfEvent;
import ru.practicum.mainservice.exceptions.ConflictModerationException;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.exceptions.UncorrectRequestException;
import ru.practicum.mainservice.mappers.EventMapper;
import ru.practicum.mainservice.mappers.RequestMapper;
import ru.practicum.mainservice.mappers.UserMapper;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.Request;
import ru.practicum.mainservice.model.User;
import ru.practicum.mainservice.repositories.UserRepository;
import ru.practicum.mainservice.util.General;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final EventsService eventsService;
    private final RequestService requestService;
    private final UserRepository userRepository;
    private final CategoriesService categoriesService;

    @Override
    public List<UserDto> getInfoAboutUser(List<Integer> ids, Integer from, Integer size) {
        log.info("Запрос на получение пользователей получен");
        if (ids == null) {
            return userRepository.findAll(General.toPage(from, size))
                    .map(UserMapper::toUserDto).getContent();
        } else {
            return userRepository.findByIdIn(ids, General.toPage(from, size)).stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        }
    }

    @Override
    public UserDto addNewUser(NewUserRequest newUserRequest) {
        User user = UserMapper.toUser(newUserRequest);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден с id" + userId)));
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь с таким id не найден"));
    }

    @Override
    public List<ParticipationRequestDto> getInfoAboutRequestsForParticipationCurrentUser(Integer userId, Integer eventId) {
        findUserById(userId);
        if (!eventsService.getEventById(eventId).getInitiator().getId().equals(userId)) {
            throw new UncorrectRequestException("Пользователь не является инициатором данного события");
        }
        List<Request> requests = requestService.findAllByEvent(eventId);
        return requests.stream().map(RequestMapper::toRequestDto).collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto addRequestOfCurrentUserForParticipateInEvent(Integer userId, Integer eventId) {

        Event event = eventsService.getEventById(eventId);
        User user = findUserById(userId);
        if(requestService.findByEventAndRequester(eventId,userId)!=null){
            throw new ConflictModerationException("Такой запрос уже существует");
        }
        if (event.getInitiator().equals(user)) {
            throw new ValidationException("The user is the initiator of the event");
        }
        if ((event.getParticipantLimit() != 0) && (!(event.getParticipantLimit() > requestService.countConfirmedRequest(eventId)))) {
            throw new ValidationException("Reached limit for participation in the event");
        }
        if (!event.getState().equals(StateOfEvent.PUBLISHED)) {
            throw new ValidationException("Event must be published");
        }
        if (requestService.validateParticipateOfUser(userId, eventId) != null) {
            throw new ValidationException("User has already created a request");
        }
        return requestService.createRequest(user, event);
    }

    @Override//
    public List<EventFullDto> searchOfEvents(List<Integer> users, List<String> states, List<Integer> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size) {
        log.info("Поиск событий по параметрам");
        if (users != null) {
            users.forEach(userRepository::findById);
        }
        if (states != null) {
            states.forEach(i -> {
                try {
                    StateOfEvent.valueOf(i);
                } catch (IllegalArgumentException e) {
                    throw new ValidationException("Incorrect state");
                }
            });
        }
        if (categories != null) {
            categories.forEach(categoriesService::findById);
        }
        if (rangeStart != null && rangeEnd != null) {
            if (rangeStart.isAfter(rangeEnd)) {
                throw new UncorrectRequestException("Start date cannot be before than end date");
            }
        }
        return eventsService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);

    }

    @Override
    public EventFullDto postEvent(Integer userId, NewEventDto newEventDto) {
        eventsService.getLocationService().add(newEventDto.getLocation());
        Event event = EventMapper.toEvent(
                findUserById(userId),
                eventsService.getCategoriesService().findById(newEventDto.getCategory()),
                newEventDto);
        if (event.getEventDate().isBefore(LocalDateTime.now())) {
            throw new UncorrectRequestException("Событие должно быть в будущем");
        }

        return EventMapper.toEventFullDto(0, 0, eventsService.addEvent(event));
    }

    @Override
    public List<EventShortDto> getEvent(Integer userId, Integer from, Integer size) {
        findUserById(userId);
        return eventsService.getEventsUser(userId, General.toPage(from, size));
    }


}
