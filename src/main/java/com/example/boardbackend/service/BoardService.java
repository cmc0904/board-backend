package com.example.boardbackend.service;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {

    ResponseResult uploadBoard(BoardDTO boardDTO);
    List<BoardVO> getBoards(Integer currentPage, String searchType, String searchContent, LocalDateTime startDate, LocalDateTime endDate);

}
