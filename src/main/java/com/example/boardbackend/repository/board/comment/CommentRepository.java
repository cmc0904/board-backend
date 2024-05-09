package com.example.boardbackend.repository.board.comment;

import com.example.boardbackend.dto.CommentDTO;
import com.example.boardbackend.vo.comment.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {

    // 댓글 추가
    void insertComment(CommentDTO commentDTO);

    
    // 게시판 PK 로 댓글 정보 불러오기
    List<CommentVO> getCommentsByBoardIdx(Integer boardIdx);
}
