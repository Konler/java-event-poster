package ru.practicum.mainservice.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainservice.exceptions.ApiError;
import ru.practicum.mainservice.exceptions.NotFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleValidationException(NotFoundException e) {
        log.info("Ошибка 404!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Неверно составлен запрос",
                HttpStatus.NOT_FOUND.name(),
                LocalDateTime.now());
    }

    private String getAsString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
