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

}
