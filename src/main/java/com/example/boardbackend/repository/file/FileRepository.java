package com.example.boardbackend.repository.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    void saveFile(Integer boardIdx, MultipartFile[] multipartFiles) throws IOException;
}
