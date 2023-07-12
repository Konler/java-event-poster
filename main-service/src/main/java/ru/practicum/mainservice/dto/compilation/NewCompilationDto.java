package ru.practicum.mainservice.dto.compilation;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Size;
import java.util.List;
@Builder
@Data
public class NewCompilationDto {
    @UniqueElements
    List<Integer> events;
    @Value("false")
    Boolean pinned;
    @Size(min = 1, max = 50, message = "Длина заголовка подборки должна быть от 1 до 50")
    String title;
}
