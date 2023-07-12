package ru.practicum.mainservice.dto.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
@Data
@Builder
public class EventRequestStatusUpdateResult {
    ParticipationRequestDto confirmedRequests;
    ParticipationRequestDto rejectedRequests;
}
