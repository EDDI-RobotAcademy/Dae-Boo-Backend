package com.example.teamproject.comment.service;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.comment.controller.form.RequestCommentForm;
import com.example.teamproject.comment.dto.CommentDto;
import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.comment.repository.CommentRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    final private CommentRepository commentRepository;
    final private BoardRepository boardRepository;
    final private UserRepository userRepository;
    @Override
    public List<Comment> listCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoard_BoardId(boardId);
    }
    @Override
    public Comment register(Comment comment) {
        return commentRepository.save(comment);
    }
    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    @Override
    public Comment modify(Long commentId, RequestCommentForm request) {
        Optional<Comment> maybeComment = commentRepository.findById(commentId);

        if (maybeComment.isEmpty()) {
            log.info("정보가 없습니다.");
            return null;
        }
        Comment comment = maybeComment.get();
        comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public Comment createComment(CommentDto commentDto) {
        String writer = commentDto.getWriter();
        String content = commentDto.getContent();
        Long boardId = commentDto.getBoardId();
        Long userId = commentDto.getUserId();

        Board board = boardRepository.findById(boardId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        Comment comment = new Comment(writer, content);
        comment.setBoard(board);
        comment.setUser(user);

        return commentRepository.save(comment);
    }
}
