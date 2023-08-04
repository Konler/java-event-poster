package ru.practicum.mainservice.exceptions;

public class UncorrectRequestException extends RuntimeException {
    public UncorrectRequestException(String name) {
        super(name);
    }
}
