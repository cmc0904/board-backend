package com.example.boardbackend.vo.board;

import com.example.boardbackend.vo.res.ResponseResult;
import lombok.Getter;

@Getter
public class DeleteErrorVO extends ResponseResult {
    private final Integer commentErrorCount;
    private final Integer boardErrorCount;

    public DeleteErrorVO(String message, Integer commentErrorCount, Integer boardErrorCount) {
        super(message);
        this.commentErrorCount = commentErrorCount;
        this.boardErrorCount = boardErrorCount;
    }
}
