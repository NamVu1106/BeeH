package com.beearena.controller;

import com.beearena.entity.LichSuTonKho;
import com.beearena.service.LichSuTonKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lich-su-ton-kho")
public class LichSuTonKhoController {
    @Autowired
    private LichSuTonKhoService lichSuTonKhoService;

    @GetMapping
    public ResponseEntity<List<LichSuTonKho>> layTatCaLichSu() {
        return ResponseEntity.ok(lichSuTonKhoService.layTatCaLichSu());
    }

    @GetMapping("/san-pham/{maSP}")
    public ResponseEntity<List<LichSuTonKho>> layTheoSanPham(@PathVariable Long maSP) {
        return ResponseEntity.ok(lichSuTonKhoService.layTheoSanPham(maSP));
    }

    @GetMapping("/loai-phat-sinh/{loaiPhatSinh}")
    public ResponseEntity<List<LichSuTonKho>> layTheoLoaiPhatSinh(@PathVariable String loaiPhatSinh) {
        return ResponseEntity.ok(lichSuTonKhoService.layTheoLoaiPhatSinh(loaiPhatSinh));
    }

    @GetMapping("/thoi-gian")
    public ResponseEntity<List<LichSuTonKho>> layTheoKhoangThoiGian(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime tuNgay,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime denNgay) {
        return ResponseEntity.ok(lichSuTonKhoService.layTheoKhoangThoiGian(tuNgay, denNgay));
    }

    @PostMapping
    public ResponseEntity<LichSuTonKho> themLichSu(@RequestBody LichSuTonKho lichSu) {
        return ResponseEntity.ok(lichSuTonKhoService.themLichSu(lichSu));
    }
} 