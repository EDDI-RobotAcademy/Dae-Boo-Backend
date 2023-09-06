package com.example.teamproject.QuestionBoard.entity;

import com.example.teamproject.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String title;
    private String contents;

    // 객체의 연관관계
    // Question N : 1 User
    // User 엔티티와의 다대일(N:1) 관계를 정의
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne
    private User user;

    private String answerContents;
    private boolean isAnswerComplete = false; // 답변이 작성됐다면 true, 작성되지 않았다면 false
    private LocalDateTime answerAt;

    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean isDeleted = false; // 삭제된 데이터라면 true, 삭제되지 않은 데이터라면 false


    public Question(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }
}


