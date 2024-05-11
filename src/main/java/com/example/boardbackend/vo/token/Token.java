package com.example.boardbackend.vo.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Token {
    private Integer allowedBoardIdx;
    private boolean isExpired;
}
