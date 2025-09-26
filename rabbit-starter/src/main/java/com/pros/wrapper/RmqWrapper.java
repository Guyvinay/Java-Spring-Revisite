package com.pros.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

@Slf4j
public class RmqWrapper {

    private RabbitTemplate rabbitTemplate;

    public RmqWrapper(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void convertAndSend(String routingKey, Object object) {
        log.info("for tenant {} routing new message to {}", "vinay", routingKey);
        try {
            SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), "vinay");
            Message message = null;
            if (object instanceof byte[]) {
                message = MessageBuilder.withBody((byte[]) object)
                        .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                        .setMessageId(UUID.randomUUID().toString())
                        .build();
            } else {
                message = MessageBuilder.withBody(new String(String.valueOf(object)).getBytes())
                        .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                        .setMessageId(UUID.randomUUID().toString())
                        .build();
            }
            rabbitTemplate.convertAndSend(routingKey, message);
        } catch (Exception e) {
            log.info("Exception while sending message");
            System.out.println(e.getMessage());
        } finally {
            SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
        }
    }

}
