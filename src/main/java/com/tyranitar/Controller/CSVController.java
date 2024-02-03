package com.tyranitar.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tyranitar.model.File;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", allowedHeaders = "") 
public class CSVController {
	
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
	    try {
	        List<ObjectNode> jsonDataList = new ArrayList<>();

	        try (Scanner scanner = new Scanner(file.getInputStream())) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();

	                // Dividir a linha usando ponto e vírgula como delimitador
	                String[] row = line.split(";");

	                if (row.length > 6) {
	                    String uuid = row[0];
	                    String data = row[1];
	                    String cpf = row[2];
	                    String nome = row[3];
	                    int idade = Integer.parseInt(row[4]);
	                    double valor = Double.parseDouble(row[5]);
	                    int parcelas = Integer.parseInt(row[6]);

	                    File file1 = new File();
	                    file1.setUuid(0);
	                    file1.setData(data);
	                    file1.setCpf(0);
	                    file1.setNome(nome);
	                    file1.setIdade(idade);
	                    file1.setValor(valor);
	                    file1.setParcelas(parcelas);

	                    // Criar um objeto JSON para cada linha
	                    ObjectMapper objectMapper = new ObjectMapper();
	                    ObjectNode jsonData = objectMapper.createObjectNode();
	                    jsonData.put("UUID", uuid);
	                    jsonData.put("Data", data);
	                    jsonData.put("Nome", file1.getNome());
	                    jsonData.put("Idade", file1.getIdade());
	                    jsonData.put("Valor", valor);

	                    jsonDataList.add(jsonData);
	                } else {
	                    // Lide com a situação em que a linha não tem elementos suficientes
	                    System.err.println("A linha não possui elementos suficientes: " + Arrays.toString(row));
	                }
	            }
	        }

	        // Converter a lista para JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        ArrayNode jsonArray = objectMapper.valueToTree(jsonDataList);

	        return new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Erro ao processar o arquivo CSV: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
