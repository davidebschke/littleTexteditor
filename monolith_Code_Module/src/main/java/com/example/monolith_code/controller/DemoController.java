package com.example.monolith_code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class DemoController {

    @GetMapping("/")
    public String helloWorld() {
        return "helloWorldTemplate";
    }
}
