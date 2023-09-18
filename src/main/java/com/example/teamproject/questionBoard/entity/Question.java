package com.example.teamproject.questionBoard.entity;

import com.example.teamproject.answer.entity.Answer;
import com.example.teamproject.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
// 엔티티 조회할 때 항상 "isDeleted"라는 필드가 "false"인 엔티티만을 반환하도록 설정
@Where(clause = "isDeleted = false")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String title;

    private String contents;

    @Setter
    private boolean isAnswerComplete = false; // 답변이 작성됐다면 true, 작성되지 않았다면 false

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isDeleted = false; // 삭제된 데이터라면 true, 삭제되지 않은 데이터라면 false

    // 객체의 연관관계
    // Question N : 1 User
    // User 엔티티와의 다대일(N:1) 관계를 정의
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne
    private User user;

    @JoinColumn(name = "answerId", referencedColumnName = "answerId")
    @ManyToOne
    private Answer answer;

    public Question(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public void modify(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void delete() {
        // Soft Delete (데이터는 살려두되, 사용자가 삭제됐다고 느끼게 하는것)
        this.isDeleted = true;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}


