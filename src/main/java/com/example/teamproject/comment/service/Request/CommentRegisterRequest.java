package com.example.teamproject.comment.service.Request;

import com.example.teamproject.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRegisterRequest {
    final private String writer;
    final private String content;
    public Comment toComment() {
        return new Comment(writer, content);
    }
}