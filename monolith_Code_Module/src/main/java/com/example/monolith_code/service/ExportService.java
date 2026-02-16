package com.example.monolith_code.service;


import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public void exportTxt(String textContent) {
        System.out.println("Exportiere Text: " + textContent);
    }
}
