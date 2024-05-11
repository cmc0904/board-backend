package com.example.boardbackend.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordAndBoardIdxDTO {
    private Integer boardIdx;
    private String password;
}
