package ru.practicum.mainservice.exceptions;

public class ConflictModerationException extends RuntimeException {
    public ConflictModerationException(String message) {
        super(message);
    }
}
