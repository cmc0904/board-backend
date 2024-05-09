package com.example.boardbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentDTO {
    private Long boardIdx;
    private String content;
    private String password;
}
