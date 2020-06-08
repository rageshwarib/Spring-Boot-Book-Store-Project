package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.RabbitMq;
import com.bridgelabz.bookstore.dto.RabbitMqDto;
import com.bridgelabz.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    RabbitMq rabbitMq;
    @Autowired
    private RabbitMqDto rabbitMqDto;


    public void sendEmailNotification(User user) throws MailException {
        rabbitMqDto.setTo(user.getEmail());
        rabbitMqDto.setFrom("ragu.bodke@gmail.com");
        rabbitMqDto.setSubject("Welcome to Book Store");
        rabbitMqDto.setBody("Click this link to verify your account" +user.getId());
        rabbitMq.sendingMsgToQueue(rabbitMqDto);
    }
}
