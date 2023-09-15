package com.example.teamproject.board_management.entity;

import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmRootEntityType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private BoardCategory category;
    @Setter
    private String boardName;
    @Setter
    private String content;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime boardRegisterDate;

    @Setter
    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;

//    public Board(BoardCategory category, String boardName, String content, User userId) {

    public Board(BoardCategory category, String boardName, String content, String writer) {
        this.category = category;
        this.boardName = boardName;
        this.content = content;
//        this.userId = userId;
        this.writer = writer;
    }
    @Setter
    private String writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime modifyData;

    @Setter
    @OrderBy("commentId")
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> comments;

}
