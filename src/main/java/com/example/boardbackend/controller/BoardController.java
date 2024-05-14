package com.example.boardbackend.controller;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.service.board.BoardService;
import com.example.boardbackend.vo.board.BoardData;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    @PutMapping("/editBoard")
    public ResponseResult edit(
            @RequestParam(value = "idx") Integer idx,
            @RequestParam(value = "title") String title
            , @RequestParam(value = "article") String article
            , @RequestParam(value = "writer") String writer
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "email") String email
            , @RequestParam(value = "isNotice") Integer isNotice
            , @RequestParam(value = "isPrivate") Integer isPrivate
            , @RequestParam(value = "files", required = false)  MultipartFile[] files
    ) {
        return boardService.editBoard(
                new BoardDTO(idx, title, article, writer, password, email, isNotice, isPrivate, files)
        );
    }


    @GetMapping("/getBoards")
    public BoardData getBasicBoards(Integer currentPage, String searchType, String content, String startDate, String endDate) {
        System.out.println(searchType);
        System.out.println(content);
        return boardService.getBoards(currentPage, searchType, content, startDate, endDate);
    }

    @GetMapping("/getNotice")
    public List<BoardVO> getNoticeBoards() {
        return boardService.getNotice();
    }

    @GetMapping("/getBoardByBoardIdx")
    public BoardVO getBoardByBoardIdx(Integer boardIdx) {
        return boardService.getBoardDataByBoardIdx(boardIdx);
    }

    @GetMapping("/getFileNamesByBoardIdx")
    public List<String> getAttachedFileNameByBoardIdx(Integer boardIdx) {
        return boardService.getAttachedFileNameByBoardIdx(boardIdx);
    }

    @DeleteMapping("/deleteBoard")
    public ResponseResult deleteBoard(@RequestBody PasswordAndBoardIdxDTO passwordAndBoardIdxDTO) {
        return boardService.deleteBoard(passwordAndBoardIdxDTO);
    }

    @GetMapping("/fileDownload")
    public byte[] fileDownloader(Integer boardIdx, String fileName) {
        return boardService.getFileByteArray(boardIdx, fileName);
    }

    @PutMapping("/readBoard")
    public ResponseResult fileDownloader(Integer boardIdx) {
        return boardService.readBoard(boardIdx);
    }
}
