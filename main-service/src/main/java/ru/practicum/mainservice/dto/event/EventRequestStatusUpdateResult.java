package ru.practicum.mainservice.dto.event;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.request.ParticipationRequestDto;

import java.util.List;

@Data
@Builder
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests;
    private List<ParticipationRequestDto> rejectedRequests;

}
