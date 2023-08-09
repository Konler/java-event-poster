package ru.practicum.mainservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {
    private String errors;
    private String message;
    private String reason;
    private String status;
    public LocalDateTime timestamp;
}
