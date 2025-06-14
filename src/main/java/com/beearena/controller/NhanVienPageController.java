package com.beearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhanVienPageController {
    @GetMapping("/nhanVien/NhanVienIndex")
    public String nhanVienIndex() {
        return "nhanVien/NhanVienIndex";
    }
} 