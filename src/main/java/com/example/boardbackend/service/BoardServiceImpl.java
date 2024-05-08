package com.example.boardbackend.service;

import com.example.boardbackend.dto.BoardDTO;
import com.example.boardbackend.repository.board.board.BoardRepository;
import com.example.boardbackend.repository.file.FileRepository;
import com.example.boardbackend.vo.board.BoardVO;
import com.example.boardbackend.vo.res.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<BoardVO> getBoards(Integer currentPage, String searchType, String searchContent, LocalDateTime startDate, LocalDateTime endDate) {
        return boardRepository.getBoards(currentPage, searchContent, startDate, endDate);
    }


}
