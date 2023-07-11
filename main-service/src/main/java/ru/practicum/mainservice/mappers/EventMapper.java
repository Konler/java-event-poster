package ru.practicum.mainservice.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.dto.EventFullDto;
import ru.practicum.mainservice.dto.EventShortDto;
import ru.practicum.mainservice.model.UpdateEventAdminRequest;
import ru.practicum.mainservice.model.entities.Event;
import ru.practicum.mainservice.repositories.EventRepository;
@UtilityClass
public class EventMapper {
   EventShortDto fromEventToEventShortDto(Event event){
       return EventShortDto.builder()
               .id(event.getId())
               .annotation(event.getAnnotation())
               .category(event.getCategory())
               .confirmedRequests(event.getConfirmedRequests())
               .eventDate(event.getEventDate())
               .initiator(event.getInitiator())
               .paid(event.getPaid())
               .title(event.getTitle())
               .views(event.getViews())
               .build();
   }

    EventFullDto fromEventToEventFullDto(Event event){
        return EventFullDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(event.getInitiator())
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

//   Event fromUpdateEventAdminRequestToEvent(UpdateEventAdminRequest updateEventAdminRequest){
//       return Event.builder()
//               .annotation(updateEventAdminRequest.getAnnotation())
//               .category(updateEventAdminRequest.getCategory())
//               .description(updateEventAdminRequest.getDescription())
//               .eventDate(updateEventAdminRequest.getEventDate())
//               .location(updateEventAdminRequest.getLocation())
//               .paid(updateEventAdminRequest.getPaid())
//               .participantLimit(updateEventAdminRequest.getParticipantLimit())
//               .requestModeration(updateEventAdminRequest.getRequestModeration())
//               .state(updateEventAdminRequest.getStateAction())
//               .build();
//    }
}
