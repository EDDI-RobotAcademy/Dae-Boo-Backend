package com.example.teamproject.comment.controller.form;


import com.example.teamproject.comment.entity.Comment;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommentForm {
     private String writer;
     private String content;
     private long userId;
     private long boardId;

    public Comment toComment() {
        return new Comment(writer, content);
    }
}
