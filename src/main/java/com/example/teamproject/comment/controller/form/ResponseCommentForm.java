package com.example.teamproject.comment.controller.form;

import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.user.dto.UserInfoResponse;
import com.example.teamproject.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseCommentForm {
    final private Long commentId;
    final private long boardId;
    final private long userId;
    final private String writer;
    final private String content;
    final private LocalDateTime createDate;

    public static ResponseCommentForm from(Comment comment) {
        return new ResponseCommentForm(
                comment.getCommentId(),
                comment.getBoard().getBoardId(),
                comment.getUserId().getUserId(),
                comment.getWriter(),
                comment.getContent(),
                comment.getCreateDate()
        );
    }
}
