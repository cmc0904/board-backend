package com.example.boardbackend.vo.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardVO {
    private Integer boardIdx;
    private String boardTitle;
    private String boardArticle;
    private String boardWriterEmail;
    private Integer fileCount;
    private String writer;
    private LocalDateTime createAt;
    private Integer isNotice;
    private Integer isPrivate;
    private Integer isNewBoard;
    private int commentCount;
    private Long boardView;
}
