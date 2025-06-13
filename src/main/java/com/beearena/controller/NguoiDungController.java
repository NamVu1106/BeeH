package com.beearena.controller;

import com.beearena.entity.NguoiDung;
import com.beearena.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping
    public List<NguoiDung> layTatCaNguoiDung() {
        return nguoiDungService.layTatCaNguoiDung();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> layNguoiDungTheoId(@PathVariable Long id) {
        return nguoiDungService.layNguoiDungTheoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<NguoiDung> layNguoiDungTheoEmail(@PathVariable String email) {
        return nguoiDungService.layNguoiDungTheoEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/dang-ky")
    public ResponseEntity<NguoiDung> dangKyNguoiDung(@RequestBody NguoiDung nguoiDung) {
        if (nguoiDungService.kiemTraEmailTonTai(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(nguoiDungService.dangKyNguoiDung(nguoiDung));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NguoiDung> capNhatNguoiDung(@PathVariable Long id, @RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.layNguoiDungTheoId(id)
                .map(existingNguoiDung -> {
                    nguoiDung.setId(id);
                    return ResponseEntity.ok(nguoiDungService.capNhatNguoiDung(nguoiDung));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaNguoiDung(@PathVariable Long id) {
        return nguoiDungService.layNguoiDungTheoId(id)
                .map(nguoiDung -> {
                    nguoiDungService.xoaNguoiDung(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 