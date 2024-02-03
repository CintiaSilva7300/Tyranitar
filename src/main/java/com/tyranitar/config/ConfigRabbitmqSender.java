package com.tyranitar.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigRabbitmqSender {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(String mensagem, String nameFila) {
        try {
            rabbitTemplate.convertAndSend("product", mensagem);
            System.out.println("Mensagem enviada: " + mensagem + "nome da fila: " + nameFila);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

}
