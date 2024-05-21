package com.example.boardbackend.controller;

import com.example.boardbackend.dto.Email;
import com.example.boardbackend.service.email.EmailService;
import com.example.boardbackend.vo.res.ResponseResult;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseResult sendEmail(@RequestParam(value = "from") String from,
                                    @RequestParam(value = "to") String to,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "files", required = false) MultipartFile[] files)  {

        return emailService.sendEmail(new Email(from, to, title, content, files));

    }
}
