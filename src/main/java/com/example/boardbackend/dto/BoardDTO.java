package com.example.boardbackend.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class BoardDTO {
    private Integer idx;
    private final String title;
    private final String article;
    private final String writer;
    private final String password;
    private final String email;
    private final Integer isNotice;
    private final Integer isPrivate;
    private final MultipartFile[] files;
    private final Integer replyIdx;

    public BoardDTO(String title, String article, String writer, String password, String email, Integer isNotice, Integer isPrivate, MultipartFile[] files, Integer replyIdx) {
        this.title = title;
        this.article = article;
        this.writer = writer;
        this.password = password;
        this.email = email;
        this.isNotice = isNotice;
        this.isPrivate = isPrivate;
        this.files = files;
        this.replyIdx = replyIdx;
    }
}
