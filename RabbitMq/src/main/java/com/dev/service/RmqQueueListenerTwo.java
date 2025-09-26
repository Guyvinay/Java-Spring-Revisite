package com.dev.service;

import com.dev.rmq.annotation.RabbitListener;
import com.dev.rmq.utility.Queues;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@RabbitListener(value = "RmqQueueListenerTwo", queue = Queues.QUEUE2)
public class RmqQueueListenerTwo implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
