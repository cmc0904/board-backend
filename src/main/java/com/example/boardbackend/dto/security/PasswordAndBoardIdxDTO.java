package com.example.boardbackend.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PasswordAndBoardIdxDTO {
    private Integer boardIdx;
    private String password;
}
