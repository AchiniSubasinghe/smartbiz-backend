package com.smartbiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String link) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("SmartBiz - Reset Your Password");
        msg.setText(
                "Hello,\n\n" +
                        "Click the link below to reset your password:\n" +
                        link +
                        "\n\nThis link will expire in 30 minutes.\n\n" +
                        "SmartBiz Team"
        );

        mailSender.send(msg);
    }
}
