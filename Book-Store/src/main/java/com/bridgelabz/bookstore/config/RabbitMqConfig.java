package com.bridgelabz.bookstore.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String topicExchangeName = "msgQueueExchange";
    public static final String queueName = "msgQueue";
    public static final String routingKey = "msgRoutingKey";

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(topicExchangeName);
    }
    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }
}
