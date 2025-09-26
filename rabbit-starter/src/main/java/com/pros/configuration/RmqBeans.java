package com.pros.configuration;

import com.pros.binding.QueueListenerBinding;
import com.pros.rmq.service.RmqService;
import com.pros.wrapper.RmqWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RmqBeans {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitProperties rabbitProperties;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    RmqService rmqService() {
        log.info("RmqService bean created");
        return new RmqService(rabbitTemplate, rabbitProperties, rabbitAdmin, restTemplate);
    }

    @Bean
    RmqWrapper rmqWrapper() {
        log.info("Rmq Wrapper Bean created");
        return new RmqWrapper(rabbitTemplate);
    }

    @Bean
    QueueListenerBinding queueListenerBinding() {
        return new QueueListenerBinding();
    }




}
