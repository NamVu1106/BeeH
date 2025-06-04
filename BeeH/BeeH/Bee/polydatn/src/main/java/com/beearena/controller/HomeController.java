package com.beearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.annotation.PostConstruct;
import java.awt.Desktop;
import java.net.URI;

@Controller
public class HomeController {

    @PostConstruct
    public void init() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8081/"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
} 