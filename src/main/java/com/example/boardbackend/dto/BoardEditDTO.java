package com.example.boardbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
public class BoardEditDTO {
    private final Integer idx;
    private final String title;
    private final String article;
    private final String writer;
    private final String password;
    private final String email;
    private final Integer isNotice;
    private final Integer isPrivate;
    private final MultipartFile[] files;
    private final String[] beforeFiles;

}
