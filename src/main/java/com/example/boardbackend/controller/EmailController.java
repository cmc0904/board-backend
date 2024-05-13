package com.example.boardbackend.controller;

import com.example.boardbackend.dto.Email;
import com.example.boardbackend.service.email.EmailService;
import com.example.boardbackend.vo.res.ResponseResult;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/mail")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public ResponseResult sendEmail(@RequestBody Email email)  {
        System.out.println(email);
        return emailService.sendEmail(email);
    }
}
