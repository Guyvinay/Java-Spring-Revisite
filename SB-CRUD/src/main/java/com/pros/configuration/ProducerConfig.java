package com.pros.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;


//@Configuration
public class ProducerConfig {

//				@Bean
//				public Queue queue(){
//								return new Queue("hello");
//				}
//				// public void sendMessage(String message){
//				// 				rabbitTemplate.convertAndSend("hello", message);
//				// 				System.out.println("hello");
//				// }
//				@Bean
//				public Queue myQueue() {
//								return new Queue("myQueue");
//				}
//
//				@Bean
//				public Queue highPriorityQueue() {
//								return new Queue("highPriorityQueue");
//				}
//				@Bean
//				public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
//								return new Jackson2JsonMessageConverter(objectMapper);
//				}


}
