package ru.practicum.mainservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.ServerClient;
import ru.practicum.mainservice.dto.event.*;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.enums.EventSort;
import ru.practicum.mainservice.enums.StateOfEvent;
import ru.practicum.mainservice.enums.StatusRequest;
import ru.practicum.mainservice.exceptions.*;
import ru.practicum.mainservice.mappers.CategoryMapper;
import ru.practicum.mainservice.mappers.EventMapper;
import ru.practicum.mainservice.mappers.RequestMapper;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.QEvent;
import ru.practicum.mainservice.model.Request;
import ru.practicum.mainservice.model.Stats;
import ru.practicum.mainservice.repositories.EventRepository;
import ru.practicum.mainservice.util.General;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventsServiceImpl implements EventsService {
    private final EventRepository eventRepository;
    private final CategoriesService categoriesService;
    private final LocationService locationService;
    private final RequestService requestService;
    private final ServerClient serverClient;

    public LocationService getLocationService() {
        return locationService;
    }

    public CategoriesService getCategoriesService() {
        return categoriesService;
    }


    @Override
    public EventFullDto updateEventsAndStatus(Integer eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Пользователь с таким id не найден" + eventId));
        if (event.getState().equals(StateOfEvent.PUBLISHED)) {
            throw new ConflictException("Событие уже опубликовано");
        }
//        if(event.getState().equals(StateOfEvent.CANCELED)){
//            throw new ConflictException("Событие уже опубликовано");
//        }

        if (updateEventAdminRequest.getEventDate() != null && updateEventAdminRequest.getEventDate().isBefore(LocalDateTime.now())) {
            log.error("Невозможно измененить дату события на уже наступившую");
            throw new UncorrectRequestException("Невозможно измененить дату события на уже наступившую");
        }
        if (updateEventAdminRequest.getAnnotation() != null) {
            event.setAnnotation(updateEventAdminRequest.getAnnotation());
        }
        if (updateEventAdminRequest.getCategory() != null) {
            event.setCategory(CategoryMapper.fromCategoryDtoToCategory(categoriesService.getCategoryById(updateEventAdminRequest.getCategory())));
        }
        if (updateEventAdminRequest.getDescription() != null) {
            event.setDescription(updateEventAdminRequest.getDescription());
        }
        if (updateEventAdminRequest.getEventDate() != null) {
            event.setEventDate(updateEventAdminRequest.getEventDate());
        }
        if (updateEventAdminRequest.getLocation() != null) {
            event.setLocation(updateEventAdminRequest.getLocation());
            locationService.add(updateEventAdminRequest.getLocation());
        }
        if (updateEventAdminRequest.getPaid() != null) {
            event.setPaid(updateEventAdminRequest.getPaid());
        }
        if (updateEventAdminRequest.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEventAdminRequest.getParticipantLimit());
        }
        if (updateEventAdminRequest.getRequestModeration() != null) {
            event.setRequestModeration(updateEventAdminRequest.getRequestModeration());
        }
        if (updateEventAdminRequest.getStateAction() != null) {
            switch (updateEventAdminRequest.getStateAction()) {
                case REJECT_EVENT:
                    if (event.getState().equals(StateOfEvent.PUBLISHED)) {
                        log.error("Нельзя отменить уже опубликованное событие");
                        throw new ConflictException("Нельзя отменить уже опубликованное событие");
                    }
                    event.setState(StateOfEvent.CANCELED);
                    break;
                case PUBLISH_EVENT:
                    if (event.getState() != StateOfEvent.PENDING) {
                        log.error("Событие должно иметь статус PENDING");
                        throw new ConflictException("Событие должно иметь статус PENDING");
                    }
                    event.setState(StateOfEvent.PUBLISHED);
                    event.setPublishedOn(LocalDateTime.now());
                    break;
            }
        }
        if (updateEventAdminRequest.getTitle() != null) {
            event.setTitle(updateEventAdminRequest.getTitle());
        }
        eventRepository.save(event);
        log.info("Событие с id " + eventId + " обновленно ");
        return EventMapper.toEventFullDto(0, 0, event);
    }

    @Override
    public List<Event> findAllByIdIn(List<Integer> eventId) {
        return eventRepository.findAllByIdIn(eventId);
    }


    @Override
    public EventFullDto getFullInfoAboutEventById(Integer userId, Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Событие с id " + eventId + "ен найденно"));

        if (Objects.equals(event.getInitiator().getId(), userId)) {
            return EventMapper.toEventFullDto(0, 0, event);
        } else {
            throw new UncorrectRequestException("Данное событие c id" + eventId + " добавленно не текущим пользователем" + userId);
        }

    }

    @Override
    public EventFullDto updateEventAddedByCurrentUser(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Событие с id " + eventId + "не найденно"));
        if (!event.getInitiator().getId().equals(userId)) {
            log.error("Только организатор может менять двнные события запроса");
            throw new UncorrectRequestException("Данное событие c id" + eventId + " добавленно не текущим пользователем" + userId);
        }
        if ((updateEventUserRequest.getEventDate() != null && updateEventUserRequest.getEventDate().isBefore(LocalDateTime.now().plusHours(2)))) {
            throw new TimeException("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
        }

        if (event.getState().equals(StateOfEvent.CANCELED) || event.getState().equals(StateOfEvent.PENDING)) {
            if (updateEventUserRequest.getAnnotation() != null) {
                event.setAnnotation(updateEventUserRequest.getAnnotation());
            }
            if (updateEventUserRequest.getCategory() != null) {
                event.setCategory(CategoryMapper.fromCategoryDtoToCategory(categoriesService.getCategoryById(updateEventUserRequest.getCategory())));
            }
            if (updateEventUserRequest.getDescription() != null) {
                event.setDescription(updateEventUserRequest.getDescription());
            }
            if (updateEventUserRequest.getEventDate() != null) {
                event.setEventDate(updateEventUserRequest.getEventDate());
            }
            if (updateEventUserRequest.getLocation() != null) {
                event.setLocation(updateEventUserRequest.getLocation());
                locationService.add(updateEventUserRequest.getLocation());
            }
            if (updateEventUserRequest.getPaid() != null) {
                event.setPaid(updateEventUserRequest.getPaid());
            }
            if (updateEventUserRequest.getParticipantLimit() != null) {
                event.setParticipantLimit(updateEventUserRequest.getParticipantLimit());
            }
            if (updateEventUserRequest.getRequestModeration() != null) {
                event.setRequestModeration(updateEventUserRequest.getRequestModeration());
            }
            if (updateEventUserRequest.getStateAction() != null) {
                switch (updateEventUserRequest.getStateAction()) {
                    case SEND_TO_REVIEW:
                        event.setState(StateOfEvent.PENDING);
                        break;
                    case CANCEL_REVIEW:
                        event.setState(StateOfEvent.CANCELED);
                        break;
                }
            }
            if (updateEventUserRequest.getTitle() != null) {
                event.setTitle(updateEventUserRequest.getTitle());
            }
            eventRepository.save(event);
            log.info("Событие с id " + eventId + " обновленно ");
            return EventMapper.toEventFullDto(0, 0, event);

        } else {
            throw new ConflictModerationException("Для редактирования события оно  должно быть отменено или находиться в ожидании");
        }
    }

    @Override
    public EventRequestStatusUpdateResult updateStatusOfParticipationEvent(Integer userId, Integer eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        Event event = getEventById(eventId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new UncorrectRequestException("Данный пользователь не является инициатором события");
        }
        if (event.getParticipantLimit() == 0 || !event.getRequestModeration()) {
            log.error("Лимит заявок равен 0 или отключена пре-модерация заявок");
            throw new ParticipantLimitException("Лимит заявок равен 0 или отключена пре-модерация заявок");
        }
        List<Integer> requestIds = eventRequestStatusUpdateRequest.getRequestIds();
        switch (eventRequestStatusUpdateRequest.getStatus()) {
            case REJECTED:
                return addStatusRejected(requestIds);
            case CONFIRMED:
                return addStatusConfirmed(requestIds, event);
            default:
                log.error("Указан неверный статус");
                throw new ConflictException("Указан неверный статус");
        }
    }

    EventRequestStatusUpdateResult addStatusConfirmed(List<Integer> requestIds, Event event) {
        List<Request> requests = requestService.findAllByIds(requestIds);
        Integer confirmedRequest = requestService.countConfirmedRequest(event.getId());/////////
        if (event.getParticipantLimit() >= confirmedRequest + requests.size()) {
            checkStatusPending(requests);
            requests.forEach(a -> a.setStatus(StatusRequest.CONFIRMED));
            requestService.saveAll(requests);
            List<ParticipationRequestDto> confirmedRequests = requests
                    .stream()
                    .map(RequestMapper::toRequestDto)
                    .collect(Collectors.toList());
            return EventRequestStatusUpdateResult.builder()
                    .rejectedRequests(List.of())
                    .confirmedRequests(confirmedRequests)
                    .build();
        } else {
            throw new ParticipantLimitException("Лимит заявок равен 0 или отключена пре-модерация заявок");
        }
    }

    EventRequestStatusUpdateResult addStatusRejected(List<Integer> requestIds) {
        List<Request> requests = requestService.findAllByIds(requestIds);
        checkStatusPending(requests);
        requests.forEach(a -> a.setStatus(StatusRequest.REJECTED));
        requestService.saveAll(requests);
        List<ParticipationRequestDto> rejectedRequests = requests
                .stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(List.of())
                .rejectedRequests(rejectedRequests)
                .build();
    }

    private void checkStatusPending(List<Request> requests) {
        boolean isConfirmedRequest = requests.stream()
                .anyMatch(r -> !r.getStatus().equals(StatusRequest.PENDING));
        if (isConfirmedRequest) {
            log.error("Статус можно изменить только у заявок со статусом PENDING");
            throw new ConflictException("Статус можно изменить только у заявок со статусом PENDING");
        }
    }

    @Override
    public List<EventShortDto> getEventsWithFiltr(String text, List<Integer> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, EventSort sort, Integer from, Integer size) {
        BooleanExpression expression;
        PageRequest pageRequest;
        BooleanBuilder builder = new BooleanBuilder();

        if (rangeStart == null && rangeEnd == null) {
            rangeStart = LocalDateTime.now();
        } else if (rangeStart != null && rangeEnd != null) {
            if (rangeStart.isAfter(rangeEnd)) {
                throw new UncorrectRequestException("Start date cannot be before than end date");
            }
        }
        if (text != null) builder.and(QEvent.event.annotation.likeIgnoreCase("%" + text.toLowerCase() + "%"))
                .or(QEvent.event.title.likeIgnoreCase("%" + text.toLowerCase() + "%"));
        if (categories != null) builder.and(QEvent.event.category.id.in(categories));
        if (paid != null) builder.and(QEvent.event.paid.eq(paid));
        if (rangeStart != null) builder.and(QEvent.event.eventDate.after(rangeStart));
        if (rangeEnd != null) builder.and(QEvent.event.eventDate.before(rangeEnd));
        if (sort != null && sort.equals(EventSort.EVENT_DATE)) {
            pageRequest = General.toPage(from, size, Sort.by("eventDate"));
        } else {
            pageRequest = General.toPage(from, size);
        }
        if (sort != null && sort.equals(EventSort.VIEWS)) {
            List<Event> events = eventRepository.findByState(StateOfEvent.PUBLISHED);
            List<EventFullDto> list = getEventFullDto(events);
            if (onlyAvailable) {
                return list.stream()
                        .filter(i -> i.getConfirmedRequests() <= i.getParticipantLimit())
                        .sorted(Comparator.comparingInt(EventFullDto::getViews))
                        .skip(from)
                        .limit(size)
                        .map(EventMapper::toEventShortDto).collect(Collectors.toList());
            } else {
                return list.stream().sorted(Comparator.comparingInt(EventFullDto::getViews))
                        .skip(from)
                        .limit(size)
                        .map(EventMapper::toEventShortDto).collect(Collectors.toList());

            }
        }
        if (builder.getValue() == null && sort != null && sort.equals(EventSort.EVENT_DATE)) {
            List<EventFullDto> list = getEventFullDto(eventRepository.findByState(StateOfEvent.PUBLISHED));
            if (onlyAvailable) {
                return list.stream()
                        .filter(i -> i.getConfirmedRequests() <= i.getParticipantLimit())
                        .sorted(Comparator.comparing(EventFullDto::getEventDate))
                        .skip(from)
                        .limit(size)
                        .map(EventMapper::toEventShortDto).collect(Collectors.toList());
            } else {
                return list.stream().sorted(Comparator.comparing(EventFullDto::getEventDate))
                        .skip(from)
                        .limit(size)
                        .map(EventMapper::toEventShortDto).collect(Collectors.toList());

            }
        }
        expression = builder.getValue() == null ? QEvent.event.isNotNull() : Expressions.asBoolean(builder.getValue());
        return getEventFullDto(eventRepository.findAll(expression, pageRequest).toList())
                .stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto getAllInfoAboutEventById(Integer id, HttpServletRequest httpServletRequest) {
        log.info("Запрос на получение полной информации о событии по id " + id);
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Событие с id" + id + "не найденно"));
        if (!event.getState().equals(StateOfEvent.PUBLISHED)) {
            log.error("Событие не найдено");
            throw new NotFoundException("Событие не найдено");
        }
        return getEventFullDto(List.of(event)).get(0);

    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Событие с данным id Не найденно"));
    }

    @Override
    public List<EventFullDto> getEvents(List<Integer> users, List<String> states, List<Integer> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size) {
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression;

        if (users != null) builder.and(QEvent.event.initiator.id.in(users));
        if (states != null) {
            states.forEach(i -> {
                try {
                    StateOfEvent.valueOf(i);
                } catch (IllegalArgumentException e) {
                    throw new ValidationException("Incorrect state");
                }
            });
            builder.and(QEvent.event.state.in(states.stream().map(StateOfEvent::valueOf).collect(Collectors.toList())));
        }
        if (categories != null) {
            builder.and(QEvent.event.category.id.in(categories));
        }
        if (rangeStart != null) {
            builder.and(QEvent.event.eventDate.after(rangeStart));
        }
        if (rangeEnd != null) {
            builder.and(QEvent.event.eventDate.before(rangeEnd));
        }
        if (builder.getValue() == null) {
            expression = QEvent.event.isNotNull();
        } else {
            expression = Expressions.asBoolean(builder.getValue());
        }
        Page<Event> events = eventRepository.findAll(expression, General.toPage(from, size));

        return getEventFullDto(events.toList());
    }

    public List<EventFullDto> getEventFullDto(List<Event> events) {
        if (events.size() == 0) return List.of();
        Map<Integer, Integer> views = getViews(events.stream()
                .map(Event::getId)
                .collect(Collectors.toList()));
        return events.stream()
                .map(i -> EventMapper.toEventFullDto(
                        requestService.countConfirmedRequest(i.getId()),
                        views.size() == 0 ? 0 : views.getOrDefault(i.getId(), 0),
                        i))
                .collect(Collectors.toList());
    }

    private Map<Integer, Integer> getViews(List<Integer> event) {
        Gson gson = new Gson();
        Stats[] views;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Integer> viewsMap = new HashMap<>();
        List<String> uris = event.stream().map(i -> "/events/" + i).collect(Collectors.toList());
        ResponseEntity<Object> response = serverClient.getStats(General.MIN_TIME, General.MAX_TIME, uris, true);
        String responseJson = gson.toJson(response.getBody());
        try {
            views = objectMapper.readValue(responseJson, Stats[].class);
        } catch (IOException e) {
            throw new InternalServerErrorException("Internal Server Error: unable to read statistics server data");
        }
        for (Stats view : views) {
            String[] lines = view.getUri().split("/");
            int a = Integer.parseInt(lines[2]);
            viewsMap.put(a, view.getHits());
        }
        return viewsMap;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventShortDto> getEventsUser(Integer userId, PageRequest pageRequest) {
        List<Event> events = eventRepository.findAllByInitiatorId(userId, pageRequest);
        return getEventFullDto(events)
                .stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

//    private void addHit(HttpServletRequest httpServletRequest) {
//        HitDto endpointHitDto = HitDto.builder()
//                .app("main-service")
//                .ip(httpServletRequest.getRemoteAddr())
//                .uri(httpServletRequest.getRequestURI())
//                .timestamp(LocalDateTime.now().toString())
//                .build();
//        serverClient.(endpointHitDto);
//    }
}
