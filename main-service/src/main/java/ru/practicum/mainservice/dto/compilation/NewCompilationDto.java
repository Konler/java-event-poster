package ru.practicum.mainservice.dto.compilation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewCompilationDto {
    @Builder.Default
    private List<Integer> events = List.of();
    @Builder.Default
    private Boolean pinned = false;

    @NotBlank
    @Size(min = 1, max = 50, message = "Длина заголовка подборки должна быть от 1 до 50")
    private String title;
}
