package com.example.demo.service.impl;

import com.example.demo.model.SanPham;
import com.example.demo.service.SanPhamService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Override
    public SanPham timTheoId(Long id) {
        SanPham sp = new SanPham();
        sp.setId(id);
        sp.setTen("Laptop ASUS ExpertBook P1 P1403CVA i5SE16 50W");
        sp.setGia(13890000L);
        sp.setGiaCu(13990000L);
        sp.setGiamPhanTram(1);
        sp.setHinhAnh("https://cdn.tgdd.vn/Products/Images/44/303226/Slider/vi-vn-asus-expertbook-p1-p1403cva-1.jpg");
        sp.setDsAnhPhu(Arrays.asList(
            "https://cdn.tgdd.vn/Products/Images/44/303226/Slider/vi-vn-asus-expertbook-p1-p1403cva-2.jpg",
            "https://cdn.tgdd.vn/Products/Images/44/303226/Slider/vi-vn-asus-expertbook-p1-p1403cva-3.jpg"
        ));
        return sp;
    }
} 