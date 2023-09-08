package com.example.teamproject.questionBoard.dto;

import com.example.teamproject.questionBoard.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailResponse {

    private long questionId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private String writerNickname;
    private boolean isAnswerComplete;
    private String answerContents;
    private LocalDateTime answerAt;

    public static QuestionDetailResponse from(Question question) {
        return new QuestionDetailResponse(
                question.getQuestionId(),
                question.getTitle(),
                question.getContents(),
                question.getCreatedAt(),
                question.getUser().getNickname(),
                question.isAnswerComplete(),
                question.getAnswerContents(),
                question.getAnswerAt()
        );
    }
}
