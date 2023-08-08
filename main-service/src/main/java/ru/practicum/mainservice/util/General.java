package ru.practicum.mainservice.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class General {
    public PageRequest toPage(Integer from, Integer size) {
        return PageRequest.of(from > 0 ? from / size : 0, size);
    }

    public static final DateTimeFormatter SERVER_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String MIN_TIME = "2000-01-01 00:00:00";

    public static final String MAX_TIME = "3000-01-01 00:00:00";
    
    public static PageRequest toPage(Integer from, Integer size, Sort sort) {
        return PageRequest.of(from > 0 ? from / size : 0, size, sort);
    }
}
