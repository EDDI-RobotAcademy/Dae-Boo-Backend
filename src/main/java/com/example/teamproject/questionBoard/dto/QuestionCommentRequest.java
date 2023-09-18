package com.example.teamproject.questionBoard.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class QuestionCommentRequest {

    private Long questionId;
    private String answer;
    private Long userId;

    public QuestionCommentRequest(Long questionId, String answer, Long userId) {
        this.questionId = questionId;
        this.answer = answer;
        this.userId = userId;
    }
}
