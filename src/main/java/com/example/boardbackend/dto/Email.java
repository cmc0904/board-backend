package com.example.boardbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
@ToString
public class Email {
    private String from;
    private String to;
    private String title;
    private String content;
    private MultipartFile[] files;
}
