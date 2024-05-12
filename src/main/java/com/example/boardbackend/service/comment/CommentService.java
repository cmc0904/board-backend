package com.example.boardbackend.service.comment;

import com.example.boardbackend.dto.CommentDTO;
import com.example.boardbackend.dto.CommentEditDTO;
import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.vo.comment.CommentVO;
import com.example.boardbackend.vo.res.ResponseResult;

import java.util.List;

public interface CommentService {

    // 댓글 추가
    ResponseResult uploadComment(CommentDTO commentDTO);

    // 게시판 번호 (PK) 로 게시판에 등록된 댓글 불러오기
    List<CommentVO> getCommentByBoardIdx(Integer boardIdx);

    // 댓글 삭제
    ResponseResult deleteComment(PasswordAndBoardIdxDTO passwordAndBoardIdxDTO);

    // 댓글 수정
    ResponseResult editComment(CommentEditDTO commentEditDTO);
}
