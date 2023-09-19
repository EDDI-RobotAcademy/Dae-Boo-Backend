package com.example.teamproject.answer.entity;

import com.example.teamproject.questionBoard.entity.Question;
import com.example.teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Setter
    private String content;

    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "questionId")
    private Question questionId;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User userId;

    public Answer(String answer, Question question, User user) {
        this.content = answer;
        this.userId = user;
        this.questionId = question;
    }
}
