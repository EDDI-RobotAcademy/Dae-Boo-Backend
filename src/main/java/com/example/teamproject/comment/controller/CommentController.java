package com.example.teamproject.comment.controller;

import com.example.teamproject.comment.dto.CommentDto;
import com.example.teamproject.comment.entity.Comment;
import com.example.teamproject.comment.service.CommentService;
import com.example.teamproject.comment.service.Request.CommentRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    final private CommentService commentService;

    @GetMapping("/list/{boardId}")
    public List<Comment> getCommentsByBoardId(@PathVariable Long boardId) {
        return commentService.listCommentsByBoardId(boardId);
    }
    @PostMapping("/register")
    public Comment registerComment (@RequestBody CommentRegisterRequest request) {
        log.info("registerComment()");
        return commentService.register(request.toComment());
    }
//    @PutMapping("/{commentId}")
//    public Comment modifyComment(@PathVariable("commentId")Long commentId, @RequestBody RequestCommentForm request) {
//        return commentService.modify(commentId, request);
//    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        log.info("deleteComment()");
        commentService.delete(commentId);
    }
    @PostMapping("/new/register")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
        Comment createdComment = commentService.createComment(commentDto);
        return ResponseEntity.ok(createdComment);
    }
}
