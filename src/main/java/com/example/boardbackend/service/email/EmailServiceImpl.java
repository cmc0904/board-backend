package com.example.boardbackend.service.email;

import com.example.boardbackend.dto.Email;
import com.example.boardbackend.vo.res.ResponseResult;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ResponseResult sendEmail(Email email) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String c = String.format("안녕하세요. %s 로 부터 전송된 메일 입니다. \n\n ---------------------------------------------\n\n %s", email.getFrom(), email.getContent());

            helper.setTo(email.getTo());
            helper.setSubject(email.getTitle());
            helper.setText(c, false);
            helper.setFrom("munchan@stninfotech.com");

            javaMailSender.send(message);
            return new ResponseResult("SENDED_EMAIL");
        } catch (Exception e) {
            return new ResponseResult("SENDING_EMAIL_ERROR");
        }
    }

}
