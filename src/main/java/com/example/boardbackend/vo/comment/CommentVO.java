package com.example.boardbackend.vo.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class CommentVO {
    private Integer idx;
    private Integer boardIdx;
    private String content;
    private String createAt;
}
