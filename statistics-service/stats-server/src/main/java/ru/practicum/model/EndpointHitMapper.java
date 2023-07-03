package ru.practicum.model;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointHitDto;


@UtilityClass
public class EndpointHitMapper {

    public static EndpointHit fromEndpointHitDto(EndpointHitDto endpointHitDto) {
        return EndpointHit.builder()
                .ip(endpointHitDto.getIp())
                .uri(endpointHitDto.getUri())
                .app(endpointHitDto.getApp())
                .created(endpointHitDto.getTimestamp())
                .build();
    }
}