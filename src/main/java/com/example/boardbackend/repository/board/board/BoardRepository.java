package com.example.boardbackend.repository.board.board;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.vo.board.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BoardRepository {
    // 게시판 글 등록
    void insertBoard(BoardDTO boardDTO);
    
    // 파일 이름 저장
    void insertFileName(Integer boardIdx, String fileName);


    // 게시판 글 목록 가져오기
    List<BoardVO> getBoards(
            Integer currentPage
            , String searchType
            , LocalDateTime startDate
            , LocalDateTime endDate
    );

    List<BoardVO> getNotice();
}
