package com.example.boardbackend.service.email;

import com.example.boardbackend.dto.Email;
import com.example.boardbackend.vo.res.ResponseResult;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    ResponseResult sendEmail(Email email);

}
