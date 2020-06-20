package com.bridgelabz.bookstore.utility;
import com.bridgelabz.bookstore.dto.EmailDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    // producer
    public void sendingMsgToQueue(EmailDto emailDto) {
    	final String exchange = "rabbitExchange";
        final String routingKey = "rabbitKey";
        amqpTemplate.convertAndSend(exchange, routingKey, emailDto);
    }

    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setFrom(email.getFrom());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
        System.out.println("Mail Sent Succesfully");
    }
  //listener
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(EmailDto email) {
        send(email);
    }
    public static EmailDto getRabbitMq(String email, String token) {
    	EmailDto emailDto = new EmailDto();
    	emailDto.setBody("Your Token is:: " + token);
    	emailDto.setTo(email);
    	emailDto.setSubject("verification link");
		return emailDto;

    }

    public SimpleMailMessage verifyUserMail(String email, String token, String link){

    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(email); 
    	message.setSubject("Hey!!! This is Verification of your mail"); //send message for user email account
    	message.setText("Token and Id "+(link+token));  //send token for  user email  account
		System.out.println("in simple mail :"+ email);
    	return message;
    }
}
