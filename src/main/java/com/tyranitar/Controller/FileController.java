package com.tyranitar.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyranitar.config.ConfigRabbitmqSender;
import com.tyranitar.model.Person;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "", allowedHeaders = "") 
public class FileController {
	
	private final ConfigRabbitmqSender configRabbitmqSender;
	
	public FileController(ConfigRabbitmqSender configRabbitmqSender) {
		this.configRabbitmqSender = configRabbitmqSender;
		
	}
	
	@PostMapping("/person")
	public ResponseEntity<String> upload (@Valid @RequestBody Person person)
			throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonProduct = objectMapper.writeValueAsString(person);

        
        configRabbitmqSender.enviarMensagem(
                jsonProduct,
                "person");

        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("Mensagem enviada para o RabbitMQ com sucesso.");
		
	}
	
}
