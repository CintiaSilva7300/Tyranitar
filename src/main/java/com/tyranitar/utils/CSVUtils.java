package com.tyranitar.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tyranitar.model.FinancialTransaction;

@Service
public class CSVUtils {

    public List<FinancialTransaction> convertFileToFinancialTransaction(MultipartFile file) throws IOException {
        List<FinancialTransaction> financialTrasactionList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file.getInputStream())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Separa as colunas usando ponto e vírgula como delimitador
                String[] row = line.split(";");

                if (row.length > 6) {
                    String id = row[0];
                    String date = row[1];
                    String cpf = row[2];
                    String name = row[3];
                    int age = Integer.parseInt(row[4]);
                    double amounte = Double.parseDouble(row[5]);
                    int installment = Integer.parseInt(row[6]);

                    FinancialTransaction financialTransaction = new FinancialTransaction();
                    financialTransaction.setId(id);
                    financialTransaction.setDate(date);
                    financialTransaction.setCpf(cpf);
                    financialTransaction.setName(name);
                    financialTransaction.setAge(age);
                    financialTransaction.setAmount(amounte);
                    financialTransaction.setInstallmentNumber(installment);

                    financialTrasactionList.add(financialTransaction);
                } else {
                    // Lide com a situação em que a linha não tem elementos suficientes
                    System.err.println("A linha não possui elementos suficientes: " + Arrays.toString(row));
                }
            }
        }
        return financialTrasactionList;
    }
}
