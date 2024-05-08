package com.example.boardbackend.controller;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.service.BoardService;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/postBoard")
    public ResponseResult postBoard(
            @RequestParam(value = "title") String title
            , @RequestParam(value = "article") String article
            , @RequestParam(value = "writer") String writer
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "email") String email
            , @RequestParam(value = "isNotice") Integer isNotice
            , @RequestParam(value = "isPrivate") Integer isPrivate
            , @RequestParam(value = "files", required = false)  MultipartFile[] files
    ) {
        return boardService.uploadBoard(
                new BoardDTO(title, article, writer, password, email, isNotice, isPrivate, files)
        );
    }


    @GetMapping("/getBoards")
    public List<BoardVO> getBoards() {
        return boardService.getBoards(null, null, null, null, null);
    }
}
