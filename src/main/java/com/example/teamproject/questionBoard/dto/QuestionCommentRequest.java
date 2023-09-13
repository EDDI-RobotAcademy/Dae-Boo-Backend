package com.example.teamproject.questionBoard.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class QuestionCommentRequest {

    private Long questionId;
    private String answer;

    public QuestionCommentRequest(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }
}
