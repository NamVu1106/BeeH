package com.beearena.controller;

import com.beearena.entity.PhieuXuat;
import com.beearena.service.PhieuXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/phieu-xuat")
public class PhieuXuatController {
    @Autowired
    private PhieuXuatService phieuXuatService;

    @GetMapping
    public ResponseEntity<List<PhieuXuat>> layTatCaPhieuXuat() {
        return ResponseEntity.ok(phieuXuatService.layTatCaPhieuXuat());
    }

    @GetMapping("/{maPX}")
    public ResponseEntity<PhieuXuat> layPhieuXuatTheoId(@PathVariable Long maPX) {
        return phieuXuatService.layPhieuXuatTheoId(maPX)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/thoi-gian")
    public ResponseEntity<List<PhieuXuat>> layTheoKhoangThoiGian(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime tuNgay,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime denNgay) {
        return ResponseEntity.ok(phieuXuatService.layTheoKhoangThoiGian(tuNgay, denNgay));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<PhieuXuat>> layTheoTrangThai(@PathVariable boolean trangThai) {
        return ResponseEntity.ok(phieuXuatService.layTheoTrangThai(trangThai));
    }

    @PostMapping
    public ResponseEntity<PhieuXuat> taoPhieuXuat(@RequestBody PhieuXuat phieuXuat) {
        try {
            return ResponseEntity.ok(phieuXuatService.taoPhieuXuat(phieuXuat));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{maPX}")
    public ResponseEntity<Void> xoaPhieuXuat(@PathVariable Long maPX) {
        try {
            phieuXuatService.xoaPhieuXuat(maPX);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 