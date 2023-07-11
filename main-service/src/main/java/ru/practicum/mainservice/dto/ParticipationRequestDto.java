package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class ParticipationRequestDto {

    LocalDateTime created;
    Integer event;
    Integer id;
    Integer requester;
    String Status;

}
