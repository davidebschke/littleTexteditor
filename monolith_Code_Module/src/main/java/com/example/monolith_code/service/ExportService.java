package com.example.monolith_code.service;


import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public String exportTxt(String textContent) {
        System.out.println("Exportiere Text: " + textContent);

        // Hier deine Export-Logik
        // z.B. Datei erstellen, in DB speichern, etc.

        return textContent;
    }

}
