package com.example.teamproject.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private String writer;
    private String content;
    private Long boardId;
    private Long userId;

    // 생성자, 게터(getter), 세터(setter) 생략
}

