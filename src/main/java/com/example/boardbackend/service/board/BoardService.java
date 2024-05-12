package com.example.boardbackend.service.board;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.vo.board.BoardData;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {

    // 게시판 글 등록
    ResponseResult uploadBoard(BoardDTO boardDTO);

    // 게시판 글 수정
    ResponseResult editBoard(BoardDTO boardDTO);

    // 공지사항 가져오기
    List<BoardVO> getNotice();

    // 일반 게시판 정보 가져오기
    BoardData getBoards(Integer currentPage, String searchType, String searchContent, LocalDateTime startDate, LocalDateTime endDate);

    // 게시판 번호(PK)로 게시판 정보 가져오기
    BoardVO getBoardDataByBoardIdx(Integer boardIdx);
    
    // 게시판 번호(PK)로 첨부된 파일 불러오기
    List<String> getAttachedFileNameByBoardIdx(Integer boardIdx);
    
    // 게시판 삭제
    ResponseResult deleteBoard(PasswordAndBoardIdxDTO passwordAndBoardIdxDTO);




}
