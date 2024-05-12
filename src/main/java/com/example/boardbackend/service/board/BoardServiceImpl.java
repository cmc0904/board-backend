package com.example.boardbackend.service.board;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.repository.board.board.BoardRepository;
import com.example.boardbackend.repository.file.FileRepository;
import com.example.boardbackend.vo.board.BoardData;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;


    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, FileRepository fileRepository) {
        this.boardRepository = boardRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public ResponseResult uploadBoard(BoardDTO boardDTO) {
        try {

            boardRepository.insertBoard(boardDTO);

            if(boardDTO.getFiles() != null) {
                fileRepository.saveFile(boardDTO.getIdx(), boardDTO.getFiles());
            }

            return new ResponseResult("UPLOAD_SUCCESSFUL");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("UPLOAD_FAILED");
        }
    }

    @Override
    public ResponseResult editBoard(BoardDTO boardDTO) {
        try {
            System.out.println(boardDTO);
            boardRepository.updateBoard(boardDTO);
            /*
            if(boardDTO.getFiles() != null) {
                fileRepository.saveFile(boardDTO.getIdx(), boardDTO.getFiles());
            }
            */
            return new ResponseResult("EDIT_SUCCESSFUL");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("EDIT_FAILED");
        }
    }

    @Override
    public List<BoardVO> getNotice() {
        try {
            return boardRepository.getNotice();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public BoardData getBoards(Integer currentPage, String searchType, String searchContent, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return new BoardData(boardRepository.getBoardCount(searchType, startDate, endDate), boardRepository.getBoards((currentPage - 1) * 10, searchContent, startDate, endDate));
        }  catch (Exception e) {
            e.printStackTrace();
            return new BoardData(0, new ArrayList<>());
        }

    }

    @Override
    public BoardVO getBoardDataByBoardIdx(Integer boardIdx) {
        return boardRepository.getBoardDataByBoardIdx(boardIdx);
    }

    @Override
    public List<String> getAttachedFileNameByBoardIdx(Integer boardIdx) {
        return boardRepository.getAttachedFileNameByBoardIdx(boardIdx);
    }

    @Override
    public ResponseResult deleteBoard(PasswordAndBoardIdxDTO passwordAndBoardIdxDTO) {
        String password = boardRepository.getPasswordByBoardIdx(passwordAndBoardIdxDTO.getBoardIdx());

        if(password.equals(passwordAndBoardIdxDTO.getPassword())) {
            try {
                boardRepository.deleteBoard(passwordAndBoardIdxDTO.getBoardIdx());
                return new ResponseResult("BOARD_DELETED");
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult("FAILED_DELETE_BOARD");
            }
        } else {
            return new ResponseResult("PASSWORD_WRONG");
        }
    }

}
