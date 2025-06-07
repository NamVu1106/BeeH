package com.beearena.controller;

import com.beearena.entity.DanhMuc;
import com.beearena.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/danh-muc")
@CrossOrigin
public class DanhMucController {
    @Autowired
    private DanhMucRepository danhMucRepository;

    @GetMapping
    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }
} 