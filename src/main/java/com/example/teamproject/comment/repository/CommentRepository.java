package com.example.teamproject.comment.repository;

import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long commentId);
    List<Comment> findByBoard_BoardId(Long boardId);

    List<Comment> findAllByUserIdAndActivateTrue(User userId);
}
