package ru.practicum.mainservice.comment.service;

import ru.practicum.mainservice.comment.dto.CommentDto;
import ru.practicum.mainservice.comment.dto.NewCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(NewCommentDto newCommentDto, Integer userId, Integer eventId);

    CommentDto renewalComment(NewCommentDto newCommentDto, Integer userId, Integer commentId);

    CommentDto getCommentById(Integer userId,  Integer commentId);

    List<CommentDto> getAllUserComments(Integer userId, Integer from, Integer size);

    void deleteCommentById(Integer userId, Integer commentId);

    List<CommentDto> getAllCommentsForEvent(Integer eventId, Integer from, Integer size);

    CommentDto renewalCommentAdmin(Integer commentId, NewCommentDto newCommentDto);

    void deleteCommentAdmin(Integer commentId);
}
