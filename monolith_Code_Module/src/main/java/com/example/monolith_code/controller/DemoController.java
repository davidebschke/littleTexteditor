package com.example.monolith_code.controller;


import com.example.monolith_code.service.ExportService;
import com.example.monolith_code.models.TextfieldText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
class DemoController {

    private final ExportService exportService;

    public DemoController(ExportService exportService) {
        this.exportService = exportService;
    }

    @PostMapping("/export")
    public String submitExportText(@ModelAttribute("textfieldText") TextfieldText textfieldText,
                                   RedirectAttributes redirectAttributes) {
        try {
            String text = textfieldText.content();

            if (text == null || text.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Bitte geben Sie Text ein!");
                return "redirect:/";
            }

            exportService.exportTxt(text);

            redirectAttributes.addFlashAttribute("success", "Text erfolgreich exportiert!");
            return "redirect:/";

        } catch (Exception e) {
            System.out.println("Es gab folgenden Fehler: " + e);
            redirectAttributes.addFlashAttribute("error", "Fehler beim Export: " + e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping
    public String helloWorld(Model model) {
        // Prüfe ob schon ein textfieldText im Model existiert (z.B. von Flash Attributes)
        if (!model.containsAttribute("textfieldText")) {
            model.addAttribute("textfieldText", new TextfieldText(""));
        }
        return "mainSide";
    }
}