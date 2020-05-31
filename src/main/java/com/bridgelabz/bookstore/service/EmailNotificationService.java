package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailNotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailNotification(User user) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("ragu.bodke@gmail.com");
        simpleMailMessage.setSubject("Welcome to Book Store");
        simpleMailMessage.setText("Click this link to verify your account" +user.getId());

        javaMailSender.send(simpleMailMessage);
    }
}
