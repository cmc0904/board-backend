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
    public BoardData getBoards(Integer currentPage, String searchType, String searchContent, String startDate, String endDate) {
        try {
            if(!searchType.equals("DATE_ONLY") && !searchType.equals("ALL_DATA")) {
                if(searchContent != null) {
                    if(searchContent.isBlank()) {
                        return new BoardData(0, searchType, new ArrayList<>());

                    }
                }
            }

            return new BoardData(boardRepository.getBoardCount(searchType, searchContent, startDate != null ? startDate + " 00:00:00" : null, endDate != null ? endDate + " 23:59:59" : null), searchType, boardRepository.getBoards((currentPage - 1) * 10, searchType, searchContent, startDate != null ? startDate + " 00:00:00" : null, endDate != null ? endDate + " 23:59:59" : null));
        }  catch (Exception e) {
            e.printStackTrace();
            return new BoardData(0, "ERROR", new ArrayList<>());
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

    @Override
    public byte[] getFileByteArray(Integer boardIdx, String fileName) {
        try {
            return fileRepository.getFileByte(boardIdx, fileName);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Override
    public ResponseResult readBoard(Integer boardIdx) {
        try {
            boardRepository.updateBoardViewCount(boardIdx);
            return new ResponseResult("BOARD_READED");
        } catch (Exception e) {
            return new ResponseResult("BOARD_READ_FAIL");
        }

    }

}
