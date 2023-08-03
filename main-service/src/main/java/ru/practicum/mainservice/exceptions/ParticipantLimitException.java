package ru.practicum.mainservice.exceptions;

public class ParticipantLimitException extends RuntimeException{
    public ParticipantLimitException(String message) {
        super(message);
    }
}
