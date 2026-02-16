package com.example.monolith_code.controller;


import com.example.monolith_code.service.ExportService;
import com.example.monolith_code.models.TextfieldText;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/")
class DemoController {

    private final ExportService exportService;

    public DemoController(ExportService exportService) {
        this.exportService = exportService;
    }

    @PostMapping("/export")
    public ResponseEntity<ByteArrayResource> submitExportText(@ModelAttribute("textfieldText") TextfieldText textfieldText
                                  ) {
        try {
            String text = textfieldText.content();

            if (text == null || text.trim().isEmpty()) {
                // Bei leerem Text: Fehler-Response
                return ResponseEntity.badRequest().build();
            }

            exportService.exportTxt(text);

            byte[] data = text.getBytes(StandardCharsets.UTF_8);
            ByteArrayResource resource = new ByteArrayResource(data);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String filename = "YourTextFile_" + timestamp + ".txt";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(data.length)
                    .body(resource);

        } catch (Exception e) {
            System.out.println("Es gab folgenden Fehler: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public String mainSide(Model model) {
        // Prüfe ob schon ein textfieldText im Model existiert (z.B. von Flash Attributes)
        if (!model.containsAttribute("textfieldText")) {
            model.addAttribute("textfieldText", new TextfieldText(""));
        }
        return "mainSide";
    }
}