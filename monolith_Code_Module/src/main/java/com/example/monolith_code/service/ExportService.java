package com.example.monolith_code.service;


import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public void exportTxt(String textContent,String fontFamily,String fontColor) {
        System.out.println("Exportiere Text: " + textContent + "mit folgender Schriftart: " + fontFamily + " und folgender Schriftfarbe: "+ fontColor);
    }
}
