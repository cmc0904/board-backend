package com.example.boardbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentEditDTO {
    private Integer commentIdx;
    private String password;
    private String content;
}
