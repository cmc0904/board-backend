package com.example.boardbackend.service.email;

import com.example.boardbackend.dto.Email;
import com.example.boardbackend.vo.res.ResponseResult;
import jakarta.mail.MessagingException;

public interface EmailService {

    ResponseResult sendEmail(Email email);

}
