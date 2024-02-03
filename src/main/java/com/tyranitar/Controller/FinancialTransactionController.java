package com.tyranitar.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tyranitar.model.FinancialTransaction;
import com.tyranitar.service.RabbitmqSenderService;
import com.tyranitar.utils.CSVUtils;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FinancialTransactionController {

	@Autowired
	RabbitmqSenderService rabbitmqSenderService;

	@Autowired
	CSVUtils csvUtils;

	@PostMapping("/financial-upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {

			List<FinancialTransaction> financialTransactionList = csvUtils.convertFileToFinancialTransaction(file);

			for (FinancialTransaction financialTransaction : financialTransactionList) {
				rabbitmqSenderService.sendMessage(
						"financial-transaction", financialTransaction.toJson());
			}

			return new ResponseEntity<>("Arquivo processado com sucesso", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao processar o arquivo CSV: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
