package com.beearena.controller;

import com.beearena.entity.NhaCungCap;
import com.beearena.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nha-cung-cap")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nhaCungCapService;

    @GetMapping
    public ResponseEntity<List<NhaCungCap>> layTatCaNhaCungCap() {
        return ResponseEntity.ok(nhaCungCapService.layTatCaNhaCungCap());
    }

    @GetMapping("/{maNCC}")
    public ResponseEntity<NhaCungCap> layNhaCungCapTheoId(@PathVariable Long maNCC) {
        return nhaCungCapService.layNhaCungCapTheoId(maNCC)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tim-kiem")
    public ResponseEntity<List<NhaCungCap>> timKiemTheoTen(@RequestParam String tenNCC) {
        return ResponseEntity.ok(nhaCungCapService.timKiemTheoTen(tenNCC));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<NhaCungCap>> layTheoTrangThai(@PathVariable boolean trangThai) {
        return ResponseEntity.ok(nhaCungCapService.layTheoTrangThai(trangThai));
    }

    @PostMapping
    public ResponseEntity<NhaCungCap> themNhaCungCap(@RequestBody NhaCungCap nhaCungCap) {
        if (nhaCungCapService.kiemTraEmailTonTai(nhaCungCap.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        if (nhaCungCapService.kiemTraSDTTonTai(nhaCungCap.getSdt())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(nhaCungCapService.themNhaCungCap(nhaCungCap));
    }

    @PutMapping("/{maNCC}")
    public ResponseEntity<NhaCungCap> capNhatNhaCungCap(
            @PathVariable Long maNCC,
            @RequestBody NhaCungCap nhaCungCap) {
        if (!nhaCungCapService.layNhaCungCapTheoId(maNCC).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        nhaCungCap.setMaNCC(maNCC);
        return ResponseEntity.ok(nhaCungCapService.capNhatNhaCungCap(nhaCungCap));
    }

    @DeleteMapping("/{maNCC}")
    public ResponseEntity<Void> xoaNhaCungCap(@PathVariable Long maNCC) {
        if (!nhaCungCapService.layNhaCungCapTheoId(maNCC).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        nhaCungCapService.xoaNhaCungCap(maNCC);
        return ResponseEntity.ok().build();
    }
} 