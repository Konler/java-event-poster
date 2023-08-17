package ru.practicum.mainservice.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.comment.dto.CommentDto;
import ru.practicum.mainservice.comment.dto.NewCommentDto;
import ru.practicum.mainservice.comment.service.CommentService;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentAdminController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public CommentDto renewalCommentAdmin(@RequestBody @Valid NewCommentDto newCommentDto,
                                          @PathVariable Integer commentId) {
        log.info("Запрос на обновление комментария с id {} администратором", commentId);
        return commentService.renewalCommentAdmin(commentId, newCommentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentAdmin(@PathVariable Integer commentId) {
        log.info("Запрос на удаление комментария с id {} администратором", commentId);
        commentService.deleteCommentAdmin(commentId);
    }
}
