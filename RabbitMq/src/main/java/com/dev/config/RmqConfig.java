package com.dev.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RmqConfig {


    @Value("${rabbitmq.queue}")
    private String queName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;


    @Bean
    public Queue queue() {
        return new Queue(queName, Boolean.FALSE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }

}