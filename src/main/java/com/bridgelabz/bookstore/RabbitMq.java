package com.bridgelabz.bookstore;



import com.bridgelabz.bookstore.config.RabbitMqConfig;
import com.bridgelabz.bookstore.dto.RabbitMqDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RabbitMq {
    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendingMsgToQueue(RabbitMqDto rabbitMqDto) {
        amqpTemplate.convertAndSend(RabbitMqConfig.topicExchangeName, RabbitMqConfig.routingKey, rabbitMqDto);
    }

    public void send(RabbitMqDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setFrom(email.getFrom());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
        System.out.println("Mail Sent Succesfully");
    }
}
