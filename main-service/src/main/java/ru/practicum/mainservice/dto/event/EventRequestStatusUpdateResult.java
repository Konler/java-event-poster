package ru.practicum.mainservice.dto.event;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;

import java.util.List;

@Data
@Builder
public class EventRequestStatusUpdateResult {
    List<ParticipationRequestDto> confirmedRequests;
    List <ParticipationRequestDto> rejectedRequests;

}
