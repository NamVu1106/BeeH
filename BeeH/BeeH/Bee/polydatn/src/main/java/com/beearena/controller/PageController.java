package com.beearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.beearena.service.SanPhamService;
import com.beearena.entity.SanPham;

@Controller
public class PageController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("/danh-muc")
    public String danhMuc() {
        return "danh-muc";
    }
    @GetMapping("/gio-hang")
    public String gioHang() {
        return "gio-hang";
    }
    @GetMapping("/tai-khoan")
    public String taiKhoan() {
        return "tai-khoan";
    }
    @GetMapping("/nha-cung-cap")
    public String nhaCungCap() {
        return "nha-cung-cap";
    }
    @GetMapping("/san-pham")
    public String sanPham() {
        return "san-pham";
    }
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
    @GetMapping("/tin-tuc")
    public String tinTuc() {
        return "tin-tuc";
    }
    @GetMapping("/don-hang")
    public String donHang() {
        return "don-hang";
    }
    @GetMapping("/lich-su")
    public String lichSu() {
        return "lich-su";
    }
    @GetMapping("/ban-phim")
    public String banPhim() {
        return "banphim";
    }
    @GetMapping("/chuot")
    public String chuot() {
        return "chuot";
    }
    @GetMapping("/tai-nghe")
    public String taiNghe() {
        return "tainghe";
    }
    @GetMapping("/mo-hinh")
    public String moHinh() {
        return "mohinh";
    }
    @GetMapping("/san-pham/{id}")
    public String chiTietSanPham(@org.springframework.web.bind.annotation.PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.laySanPhamTheoId(Long.valueOf(id)).orElse(null);
        if (sanPham == null) {
            return "redirect:/san-pham";
        }
        model.addAttribute("sanPham", sanPham);
        // Nếu có sản phẩm mua kèm, có thể lấy thêm ở đây
        return "ChiTietSanPham";
    }
} 