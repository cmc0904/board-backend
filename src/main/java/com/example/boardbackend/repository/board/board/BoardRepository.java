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

    // 게시판 글 등록
    void updateBoard(BoardDTO boardDTO);
    
    // 파일 이름 저장
    void insertFileName(Integer boardIdx, String fileName);


    // 게시판 글 목록 가져오기
    List<BoardVO> getBoards(
            Integer offset
            , String searchType
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
    
    // 게시판 총 갯수
    Integer getBoardCount(
            String searchType
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
    
    // 공지사항 불러오기
    List<BoardVO> getNotice();
    
    // 게시판 번호로 게시판 정보 불러오기
    BoardVO getBoardDataByBoardIdx(Integer boardIdx);
    
    // 게시판 번호로 첨부파일 이름 불러오기
    List<String> getAttachedFileNameByBoardIdx(Integer boardIdx);
    
    // 게시판 비빌번호 가지오기
    String getPasswordByBoardIdx(Integer boardIdx);

    // 게시판 PK 로 삭제
    void deleteBoard(Integer boardIdx);
}
