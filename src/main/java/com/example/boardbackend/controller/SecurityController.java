package com.example.boardbackend.controller;

import com.example.boardbackend.dto.security.PasswordAndBoardIdxDTO;
import com.example.boardbackend.repository.board.board.BoardRepository;
import com.example.boardbackend.vo.res.ResponseResult;
import com.example.boardbackend.vo.token.Token;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/api/security")
public class SecurityController {

    private final BoardRepository boardRepository;
    private final Gson gson;
    private static final String AES_KEY = "AES_PRIVATE_KEY_THIS_TEST_32BYTE"; // AES 키

    @Autowired
    public SecurityController(BoardRepository boardRepository, Gson gson) {
        this.boardRepository = boardRepository;
        this.gson = gson;
    }

    @PostMapping("/validateReadPermissionToken")
    public ResponseResult readPermissionChecker(String token) {
        return null;
    }


    @PostMapping("/generateReadPermissionToken")
    public ResponseResult generateReadPermissionToken(@RequestBody PasswordAndBoardIdxDTO passwordAndBoardIdxDTO) {
        String password = boardRepository.getPasswordByBoardIdx(passwordAndBoardIdxDTO.getBoardIdx());

        if (password.equals(passwordAndBoardIdxDTO.getPassword())) {
            String token = generateToken(new Token(passwordAndBoardIdxDTO.getBoardIdx(), false));
            return new ResponseResult(token);
        } else {
            return new ResponseResult("PASSWORD_WRONG");
        }
    }

    // 암호화
    private String generateToken(Token token) {
        try {
            String dataToEncrypt = gson.toJson(token);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedData = cipher.doFinal(dataToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
