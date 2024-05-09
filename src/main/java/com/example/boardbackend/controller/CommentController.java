package com.example.boardbackend.controller;

import com.example.boardbackend.dto.CommentDTO;
import com.example.boardbackend.service.comment.CommentService;
import com.example.boardbackend.vo.comment.CommentVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseResult addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.uploadComment(commentDTO);
    }

    @GetMapping("/getCommentByBoardIdx")
    public List<CommentVO> getCommentByBoardIdx(Integer boardIdx) {
        return commentService.getCommentByBoardIdx(boardIdx);
    }
}
