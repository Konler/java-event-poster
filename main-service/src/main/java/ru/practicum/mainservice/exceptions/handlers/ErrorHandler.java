package ru.practicum.mainservice.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainservice.exceptions.*;

import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(TimeException e) {
        log.info("Ошибка 409! До начала события меньше 2 часов");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "До начала события меньше 2 часов",
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleValidationException(ValidationException e) {
        log.info("Ошибка 409! ");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "The user is the initiator of the event",
                HttpStatus.CONFLICT.name(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerDataIntegrityViolationException(final DataIntegrityViolationException e) {
        log.error("Data input incorrect: {}", e.getMessage());
        return new ApiError(
                Arrays.toString(e.getStackTrace()),
                e.getMessage(),
                "Integrity constraint has been violated.",
                HttpStatus.CONFLICT.getReasonPhrase(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleValidationException(ConflictModerationException e) {
        log.info("Ошибка 409!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Нельзя редактировать пользователя с данным статусом",
                HttpStatus.CONFLICT.name(),
                LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleValidationException(InvocationTargetException e) {
        log.info("Ошибка 409!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Уже существует",
                HttpStatus.CONFLICT.name(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(UncorrectRequestException e) {
        log.info("Ошибка 400!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Неверно составлен запрос",
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now());
    }

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleParticipantLimitException(final ParticipantLimitException e) {
        log.info("Ошибка 409!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Достигнут лимит возможных участников",
                HttpStatus.CONFLICT.name(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final ConflictException e) {
        log.info("Ошибка 409!");
        return new ApiError(
                getAsString(e),
                e.getMessage(),
                "Несовпадение значений",
                HttpStatus.CONFLICT.name(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handlerInternalServerErrorException(final InternalServerErrorException e) {
        log.error(e.getMessage());
        return new ApiError(
                Arrays.toString(e.getStackTrace()),
                e.getMessage(),
                "The required object was not found.",
                HttpStatus.INTERNAL_SERVER_ERROR.name(), LocalDateTime.now());
    }

    private String getAsString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
