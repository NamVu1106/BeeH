package com.beearena.controller;

import com.beearena.entity.DonHang;
import com.beearena.enums.TrangThaiDonHang;
import com.beearena.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/don-hang")
public class DonHangController {
    @Autowired
    private DonHangService donHangService;

    @GetMapping
    public List<DonHang> layTatCaDonHang() {
        return donHangService.layTatCaDonHang();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonHang> layDonHangTheoId(@PathVariable Long id) {
        return donHangService.layDonHangTheoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nguoi-dung/{nguoiDungId}")
    public List<DonHang> layDonHangTheoNguoiDung(@PathVariable Long nguoiDungId) {
        return donHangService.layDonHangTheoNguoiDung(nguoiDungId);
    }

    @GetMapping("/trang-thai/{trangThai}")
    public List<DonHang> layDonHangTheoTrangThai(@PathVariable TrangThaiDonHang trangThai) {
        return donHangService.layDonHangTheoTrangThai(trangThai);
    }

    @PostMapping
    public DonHang taoDonHang(@RequestBody DonHang donHang) {
        return donHangService.taoDonHang(donHang);
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<DonHang> capNhatTrangThaiDonHang(
            @PathVariable Long id,
            @RequestParam TrangThaiDonHang trangThai) {
        DonHang donHang = donHangService.capNhatTrangThaiDonHang(id, trangThai);
        return donHang != null ? ResponseEntity.ok(donHang) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaDonHang(@PathVariable Long id) {
        return donHangService.layDonHangTheoId(id)
                .map(donHang -> {
                    donHangService.xoaDonHang(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 