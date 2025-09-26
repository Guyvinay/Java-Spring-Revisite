package com.pros.service;

import com.pros.annotation.QueueListener;
import com.pros.modal.RabbitMessage;
import com.pros.utils.QueueListeners;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@QueueListener(value = "RabbitConsumer", queue = QueueListeners.QUEUE2)
public class RabbitConsumer implements MessageListener {


//				@RabbitListener(queues = QueueListeners.QUEUE1)
//				public void receive(String message){
//								log.info("Rabbit Consumer Consuming message:- "+message);
//								System.out.println("Received Message:-"+message);
//				}

	@Override
	public void onMessage(Message message) {
		log.info("Receiving message from QUEUE2");
		log.info("message {}", message);
	}
//
//				@RabbitListener(queues = "myQueue")
//				public void receiveMessage(RabbitMessage message) {
//								System.out.println("Message received from myQueue:");
//								System.out.println("Sender: " + message.getSender());
//								System.out.println("Receiver: " + message.getReceiver());
//								System.out.println("Content: " + message.getContent());
//				}
//
//				@RabbitListener(queues = "highPriorityQueue")
//				public void receiveHighPriorityMessage(RabbitMessage message) {
//								System.out.println("High priority message received from highPriorityQueue:");
//								System.out.println("Sender: " + message.getSender());
//								System.out.println("Receiver: " + message.getReceiver());
//								System.out.println("Content: " + message.getContent());
//				}



}
