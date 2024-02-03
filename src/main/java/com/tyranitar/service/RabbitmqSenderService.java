package com.tyranitar.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, String message) {
        try {
            rabbitTemplate.convertAndSend("financial-transaction", message);
            System.out.println("message enviada: " + message + "nome da fila: " + queueName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
