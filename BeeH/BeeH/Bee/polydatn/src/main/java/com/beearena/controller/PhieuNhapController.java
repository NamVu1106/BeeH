package com.beearena.controller;

import com.beearena.entity.PhieuNhap;
import com.beearena.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/phieu-nhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @GetMapping
    public ResponseEntity<List<PhieuNhap>> layTatCaPhieuNhap() {
        return ResponseEntity.ok(phieuNhapService.layTatCaPhieuNhap());
    }

    @GetMapping("/{maPN}")
    public ResponseEntity<PhieuNhap> layPhieuNhapTheoId(@PathVariable Long maPN) {
        return phieuNhapService.layPhieuNhapTheoId(maPN)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nha-cung-cap/{maNCC}")
    public ResponseEntity<List<PhieuNhap>> layTheoNhaCungCap(@PathVariable Long maNCC) {
        return ResponseEntity.ok(phieuNhapService.layTheoNhaCungCap(maNCC));
    }

    @GetMapping("/thoi-gian")
    public ResponseEntity<List<PhieuNhap>> layTheoKhoangThoiGian(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime tuNgay,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime denNgay) {
        return ResponseEntity.ok(phieuNhapService.layTheoKhoangThoiGian(tuNgay, denNgay));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<PhieuNhap>> layTheoTrangThai(@PathVariable boolean trangThai) {
        return ResponseEntity.ok(phieuNhapService.layTheoTrangThai(trangThai));
    }

    @PostMapping
    public ResponseEntity<PhieuNhap> taoPhieuNhap(@RequestBody PhieuNhap phieuNhap) {
        try {
            return ResponseEntity.ok(phieuNhapService.taoPhieuNhap(phieuNhap));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{maPN}")
    public ResponseEntity<Void> xoaPhieuNhap(@PathVariable Long maPN) {
        try {
            phieuNhapService.xoaPhieuNhap(maPN);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 