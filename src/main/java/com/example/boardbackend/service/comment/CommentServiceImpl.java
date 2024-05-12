package com.example.boardbackend.service.comment;

import com.example.boardbackend.dto.CommentDTO;
import com.example.boardbackend.dto.CommentEditDTO;
import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.repository.board.board.BoardRepository;
import com.example.boardbackend.repository.board.comment.CommentRepository;
import com.example.boardbackend.vo.comment.CommentVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public ResponseResult uploadComment(CommentDTO commentDTO) {
        try {
            commentRepository.insertComment(commentDTO);

            return new ResponseResult("UPLOAD_SUCCESSFUL");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("UPLOAD_FAILED");
        }
    }

    @Override
    public List<CommentVO> getCommentByBoardIdx(Integer boardIdx) {
        return commentRepository.getCommentsByBoardIdx(boardIdx);
    }

    @Override
    public ResponseResult deleteComment(PasswordAndBoardIdxDTO passwordAndBoardIdxDTO) {
        System.out.println(passwordAndBoardIdxDTO);
        String password = commentRepository.getCommentPasswordByCommentIdx(passwordAndBoardIdxDTO.getBoardIdx());
        System.out.println(password);
        if(password.equals(passwordAndBoardIdxDTO.getPassword())) {
            try {
                commentRepository.deleteCommentByCommentIdx(passwordAndBoardIdxDTO.getBoardIdx());
                return new ResponseResult("COMMENT_DELETED");
            } catch (Exception e) {
                return new ResponseResult("COMMENT_DELETE_FAILED");
            }
        }


        return new ResponseResult("PASSWORD_WRONG");
    }

    @Override
    public ResponseResult editComment(CommentEditDTO commentEditDTO) {
        String password = commentRepository.getCommentPasswordByCommentIdx(commentEditDTO.getCommentIdx());

        if(password.equals(commentEditDTO.getPassword())) {
            try {
                commentRepository.updateComment(commentEditDTO);
                return new ResponseResult("COMMENT_EDIT");
            } catch (Exception e) {
                return new ResponseResult("COMMENT_EDIT_FAILED");
            }
        }
        return new ResponseResult("PASSWORD_WRONG");
    }
}
