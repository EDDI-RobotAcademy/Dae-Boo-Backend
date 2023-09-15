package com.example.teamproject.comment.controller.form;


import com.example.teamproject.comment.service.Request.CommentRegisterRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestCommentForm {
    final private String writer;
    final private String content;

    public CommentRegisterRequest toComment() {
        return new CommentRegisterRequest(writer,content);
    }
}
