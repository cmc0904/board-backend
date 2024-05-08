package com.example.boardbackend.repository.file;

import com.example.boardbackend.repository.board.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Repository
public class FileDirectoryRepository implements FileRepository {

    private static final String PATH = "C:\\Users\\user\\Desktop\\board\\images\\";

    private final BoardRepository boardRepository;

    @Autowired
    public FileDirectoryRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void saveFile(Integer boardIdx, MultipartFile[] files) throws IOException {
        String directory = String.format("%s\\board-%d", PATH, boardIdx);
        File saveDirectory = new File(directory);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs(); // 디렉토리 생성
        }

        for(MultipartFile file : files) {
            file.transferTo(new File(directory + "\\" + file.getOriginalFilename()));
            boardRepository.insertFileName(boardIdx, file.getOriginalFilename());
        }
    }
}
