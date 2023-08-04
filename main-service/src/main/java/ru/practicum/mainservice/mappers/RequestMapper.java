package ru.practicum.mainservice.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainservice.model.Request;
import ru.practicum.mainservice.util.General;

@UtilityClass

public class RequestMapper {
    public ParticipationRequestDto toRequestDto(Request request) {
        return ParticipationRequestDto.builder()
                .id(request.getId())
                .created(request.getCreated().format(General.SERVER_FORMAT))
                .status(request.getStatus())
                .event(request.getEvent())
                .requester(request.getId())
                .build();
    }
}
