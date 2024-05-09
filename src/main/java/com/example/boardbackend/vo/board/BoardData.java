package com.example.boardbackend.vo.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class BoardData {
    private Integer count;
    private List<BoardVO> boardData;
}
