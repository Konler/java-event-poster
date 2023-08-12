package ru.practicum.mainservice.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class NewCommentDto {

    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(min = 1, max = 7000, message = "Текст комментария должен содержать не менее 1 и не более 7000 символов")
    private String text;
}
