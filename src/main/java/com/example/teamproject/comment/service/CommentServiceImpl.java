package com.example.teamproject.comment.service;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.comment.controller.form.RequestCommentForm;
import com.example.teamproject.comment.controller.form.ResponseCommentForm;
import com.example.teamproject.comment.dto.CommentDto;
import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.comment.repository.CommentRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Comment register( RequestCommentForm requestCommentForm) {
        Comment comment = requestCommentForm.toComment();
        Optional<User> maybeUser = userRepository.findByUserId(requestCommentForm.getUserId());
        Optional<Board> maybeBoard = boardRepository.findByBoardId(requestCommentForm.getBoardId());

        if (maybeUser.isPresent()) {
            comment.setUserId(maybeUser.get());
        }
        if (maybeBoard.isPresent()) {
            comment.setBoard(maybeBoard.get());
        }
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

//    @Override
//    public Comment createComment(CommentDto commentDto) {
//        String writer = commentDto.getWriter();
//        String content = commentDto.getContent();
//        Long boardId = commentDto.getBoardId();
//        Long userId = commentDto.getUserId();
//
//        // 게시글과 사용자를 검색
//        Board board = boardRepository.findById(boardId).orElse(null);
//        User user = userRepository.findById(userId).orElse(null);
//
//        // 게시글 또는 사용자가 없을 경우 예외 처리
//        if (board == null || user == null) {
//            throw new EntityNotFoundException("게시글 또는 사용자를 찾을 수 없습니다.");
//        }
//
//        Comment comment = new Comment(writer, content, user, board);
//        comment.setBoard(board);
//        comment.setUserId(user); // setUserId 대신 setUser 사용
//
//        return commentRepository.save(comment);
//    }


    @Override
    public List<Comment> findCommentByLoginUser(User loginUser) {
        List<Comment> maybeCommentList = commentRepository.findAllByUserIdAndActivateTrue(loginUser);

        return maybeCommentList;
    }

    @Override
    public void commnetListDelete(List<Long> commentIds) {
        for(Long commentId : commentIds) {
            Optional<Comment> maybeComment= commentRepository.findById(commentId);
            if(maybeComment.isPresent()){
                Comment comment = maybeComment.get();
                comment.setActivate(false);
                commentRepository.save(comment);
            }
        }
    }
}
