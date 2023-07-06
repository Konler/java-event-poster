package ru.practicum.mainservice.model;


import ru.practicum.mainservice.StatusError;

public class ApiError {
    String [] errors;
    String message;
    String reason;
    StatusError status;
    String timestamp;
}
