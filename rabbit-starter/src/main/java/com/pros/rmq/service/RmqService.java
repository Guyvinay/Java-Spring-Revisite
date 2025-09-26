package com.pros.rmq.service;

import com.pros.utils.TenantRetriever;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;


public class RmqService {

    private static final Logger log = LoggerFactory.getLogger(RmqService.class);

    private String hostUrl;

    private String username;

    private String password;

    private RabbitProperties rabbitProperties;

    private RabbitTemplate rabbitTemplate;

    private RestTemplate restTemplate;

    private RabbitAdmin rabbitAdmin;

    @Autowired
    private TenantRetriever tenantRetriever;

    public RmqService(RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties, RabbitAdmin rabbitAdmin, RestTemplate restTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
        this.rabbitAdmin = rabbitAdmin;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void postConstruct() {
        username = rabbitProperties.getUsername();
        password = rabbitProperties.getPassword();
        hostUrl = "http://" + rabbitProperties.getHost() + ":15672" + "/api/vhosts/";
        log.info("username: {}, password: {}, hostUrl: {}", username, password, hostUrl);
        Set<String> tenants = tenantRetriever.getAllTenants();
        tenants.forEach(this::createVirtualHostAndQueues);
    }

    public void createVirtualHostAndQueues(String vhostName) {
        if (checkVHostAvailability(vhostName)) {
            log.info("virtual host already exists");
        } else {
            createVirtualHost(vhostName);
//            createQueuesAndBindings(vhostName);
        }
    }

    private void createVirtualHost(String vhostName) {
        log.info("Virtual Host creation start for {}", vhostName);
        String url = hostUrl + vhostName;
        //headers.add("Authorization", Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        //headers.setContentType(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        //Map<String, Object> body = new HashMap<>();
        //body.put("description", "vHost Description");
        //body.put("metadata", "metadata");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<?> response = restTemplate.exchange(
                url, HttpMethod.PUT, entity, new ParameterizedTypeReference<Void>() {
                }
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Virtual host created successfully, {}", vhostName);
//            createQueuesAndBindings(vhostName);
        } else {
            throw new RuntimeException("Failed to create virtual host: " + response.getStatusCode());
        }
    }

    public boolean checkVHostAvailability(String vHost) {
        ResponseEntity<?> responseValidate = null;

        try {
            String Url = hostUrl + vHost;
            log.info("Url: {}", Url);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBasicAuth(username, password);
            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
            responseValidate = restTemplate.exchange(
                    Url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
                    }
            );
            log.info("response entity {}", responseValidate);

            if (responseValidate.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                log.error("Failed to check virtual host: Status code {}", responseValidate.getStatusCodeValue());
                return false;  // Or throw a specific exception for clarity
            }
        } catch (HttpClientErrorException.NotFound e) {
            log.error("Virtual host not found: {}", e.getMessage());
            return false;
        } catch (RestClientResponseException e) {
            log.error("Error checking virtual host: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error checking virtual host: {}", e.getMessage());
            return false;
        }
    }

    private void createQueuesAndBindings(String vHost) {
        log.info("Queue creation for {} starts", vHost);
        String[] queues = {"queue1", "queue2", "queue3", "queue4", "queue5"};
        String exchange = "topic-exchange";
        try {
            SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), vHost);
            TopicExchange topicExchange = new TopicExchange(exchange);
            rabbitAdmin.declareExchange(topicExchange);
            for (String q : queues) {
                Queue queue = QueueBuilder.durable(q).build();
                rabbitAdmin.declareQueue(queue);
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(q + ".routing.key"));
            }
            log.info("Queues and bindings created for virtual host: {}", vHost);
        } catch (Exception e) {
            log.info("Exception occurred during queue creation  {}", e.getMessage());
            e.printStackTrace();
        } finally {
            SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
        }

    }
}
