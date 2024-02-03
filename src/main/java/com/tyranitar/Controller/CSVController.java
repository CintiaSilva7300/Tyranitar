package com.tyranitar.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.tyranitar.model.File;
import com.tyranitar.model.Person;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", allowedHeaders = "") 
public class CSVController {
	
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
        	
        	if (file.getSize() > 10 * 1024 * 1024) { // congfigura tamanho do arquivo csv
                return "Tamanho máximo do arquivo excedido. Limite é de 10MB.";
            }
        	
        	
            List<String[]> csvData = reader.readAll();

            for (String[] row : csvData) {
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

                    System.out.println("Name: " + file1.getNome() + ", Age: " + file1.getIdade());
                    System.out.println("UUID: " + uuid + ", Data: " + data + ", Nome: " + file1.getNome() + ", Idade: " + file1.getIdade() + ", Valor: " + valor);
                } else {
                    // Lide com a situação em que a linha não tem elementos suficientes
                    System.err.println("A linha não possui elementos suficientes: " + Arrays.toString(row));
                }
            }

            return "Arquivo CSV processado com sucesso!";
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return "Erro ao processar o arquivo CSV: " + e.getMessage();
        }
    }
}
