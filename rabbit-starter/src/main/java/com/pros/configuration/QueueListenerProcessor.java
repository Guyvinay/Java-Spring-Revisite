package com.pros.configuration;

import com.pros.annotation.QueueListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

//Not in use

//@Configuration
@Slf4j
public class QueueListenerProcessor implements BeanPostProcessor {

    @Autowired
    private ConnectionFactory connectionFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        log.info("QueueListener Processor");
        QueueListener queueListener = AnnotationUtils.findAnnotation(bean.getClass(), QueueListener.class);
        if(queueListener != null) {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.setQueueNames(queueListener.queue());
            container.setMessageListener(new MessageListenerAdapter(bean));
            container.setPrefetchCount(queueListener.prefetchCount());
            container.start();
        }
        return bean;
    }
}
