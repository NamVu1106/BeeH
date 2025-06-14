package com.beearena.controller;

import com.beearena.entity.NguoiDung;
import com.beearena.entity.VaiTro;
import com.beearena.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        try {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setEmail(body.get("email"));
            nguoiDung.setMatKhau(body.get("password"));
            nguoiDung.setHoTen(body.getOrDefault("name", ""));
            nguoiDung.setVaiTro(VaiTro.NGUOI_DUNG); // Đăng ký mặc định là user
            if (nguoiDungService.kiemTraEmailTonTai(nguoiDung.getEmail())) {
                return Map.of("success", false, "message", "Email đã tồn tại!");
            }
            nguoiDungService.dangKyNguoiDung(nguoiDung);
            return Map.of("success", true, "message", "Đăng ký thành công!");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Lỗi: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Optional<NguoiDung> userOpt = nguoiDungService.layNguoiDungTheoEmail(email);
        if (userOpt.isPresent() && userOpt.get().getMatKhau().equals(password)) {
            NguoiDung user = userOpt.get();
            boolean isAdmin = user.getVaiTro() == VaiTro.QUAN_TRI;
            boolean isNhanVien = user.getVaiTro().toString().equalsIgnoreCase("NHAN_VIEN");
            return Map.of(
                "success", true,
                "message", isAdmin ? "Đăng nhập admin thành công!" : (isNhanVien ? "Đăng nhập nhân viên thành công!" : "Đăng nhập thành công!"),
                "user", user,
                "isAdmin", isAdmin,
                "isNhanVien", isNhanVien
            );
        }
        return Map.of("success", false, "message", "Sai email hoặc mật khẩu!");
    }
} 