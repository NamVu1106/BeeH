package com.example.demo.controller;

import com.example.demo.model.SanPham;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/san-pham/{id}")
    public String chiTietSanPham(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.timTheoId(id);
        model.addAttribute("sanPham", sanPham);
        return "ChiTietSanPham";
    }
} 