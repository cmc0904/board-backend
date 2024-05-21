package com.example.boardbackend.repository.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    void saveFile(Integer boardIdx, MultipartFile[] multipartFiles) throws IOException;

    byte[] getFileByte(Integer boardIdx, String fileName) throws IOException;
    void deleteFile(Integer boardIdx, String fileName);
}
