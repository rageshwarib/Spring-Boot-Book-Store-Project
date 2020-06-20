package com.bridgelabz.bookstore.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	 	@Value("${spring.rabbitmq.template.exchange}")
	    private String exchangeName;

	    @Value("${spring.rabbitmq.template.default-receive-queue}")
	    private String queueName;

	    @Value("${spring.rabbitmq.template.routing-key}")
	    private String routingKey;

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(exchangeName);
    }
    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }
}
