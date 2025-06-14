package com.beearena.controller;

import com.beearena.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import com.beearena.entity.SanPham;
import com.beearena.entity.DanhMuc;
import com.beearena.repository.DanhMucRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

@Controller
public class SanPhamWebController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @GetMapping("/admin/quan-ly-san-pham")
    public String hienThiDanhSachSanPham(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "danhMucId", required = false) Long danhMucId,
            @RequestParam(value = "trangThai", required = false) Boolean trangThai,
            @RequestParam(value = "giaTu", required = false) Long giaTu,
            @RequestParam(value = "giaDen", required = false) Long giaDen,
            Model model, HttpServletRequest request) {
        int pageSize = 10;
        org.springframework.data.domain.Page<com.beearena.entity.SanPham> sanPhamPage = sanPhamService.locSanPham(page, pageSize, keyword, danhMucId, trangThai, giaTu, giaDen);
        model.addAttribute("sanPhamList", sanPhamPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("danhMucId", danhMucId);
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("giaTu", giaTu);
        model.addAttribute("giaDen", giaDen);
        model.addAttribute("danhMucList", danhMucRepository.findAll());
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("tongSanPham", sanPhamService.demTatCaSanPham());
        model.addAttribute("sanPhamHienThi", sanPhamService.demSanPhamHienThi());
        model.addAttribute("sanPhamAn", sanPhamService.demSanPhamAn());
        model.addAttribute("tongSoLuongTonKho", sanPhamService.tongSoLuongTonKho());
        model.addAttribute("tongGiaTriTonKho", sanPhamService.tongGiaTriTonKho());
        return "admin/quan-ly-san-pham";
    }

    @GetMapping("/admin/san-pham/sua/{id}")
    public String hienThiFormSuaSanPham(@PathVariable Integer id, Model model) {
        SanPham sp = sanPhamService.laySanPhamTheoId(Long.valueOf(id)).orElse(null);
        if (sp == null) return "redirect:/admin/quan-ly-san-pham";
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        // Danh sách ảnh mẫu (có thể lấy từ DB hoặc hardcode mẫu)
        List<Map<String, String>> anhMauList = Arrays.asList(
            Map.of("url", "/uploads/products/1.jpg", "ten", "Chuột Logitech G502"),
            Map.of("url", "/uploads/products/2.jpg", "ten", "Bàn phím cơ Keychron K2"),
            Map.of("url", "/uploads/products/3.jpg", "ten", "Tai nghe HyperX Cloud II"),
            Map.of("url", "/uploads/products/4.jpg", "ten", "Webcam Logitech C920"),
            Map.of("url", "/uploads/products/5.jpg", "ten", "Chuột Razer DeathAdder V2")
        );
        model.addAttribute("sanPham", sp);
        model.addAttribute("danhMucList", danhMucList);
        model.addAttribute("anhMauList", anhMauList);
        return "admin/sua-san-pham";
    }

    @PostMapping("/admin/san-pham/sua/{id}")
    public String capNhatSanPham(@PathVariable Integer id, @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<DanhMuc> danhMucList = danhMucRepository.findAll();
            model.addAttribute("danhMucList", danhMucList);
            return "admin/sua-san-pham";
        }
        sanPham.setMaSP(id);
        sanPhamService.luuSanPham(sanPham);
        return "redirect:/admin/quan-ly-san-pham";
    }

    @GetMapping("/admin/san-pham/xoa/{id}")
    public String xoaSanPham(@PathVariable Integer id) {
        sanPhamService.xoaSanPham(Long.valueOf(id));
        return "redirect:/admin/quan-ly-san-pham";
    }

    @GetMapping("/admin/san-pham/them")
    public String hienThiFormThemSanPham(Model model) {
        SanPham sp = new SanPham();
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        List<Map<String, String>> anhMauList = Arrays.asList(
            Map.of("url", "/uploads/products/1.jpg", "ten", "Chuột Logitech G502"),
            Map.of("url", "/uploads/products/2.jpg", "ten", "Bàn phím cơ Keychron K2"),
            Map.of("url", "/uploads/products/3.jpg", "ten", "Tai nghe HyperX Cloud II"),
            Map.of("url", "/uploads/products/4.jpg", "ten", "Webcam Logitech C920"),
            Map.of("url", "/uploads/products/5.jpg", "ten", "Chuột Razer DeathAdder V2")
        );
        model.addAttribute("sanPham", sp);
        model.addAttribute("danhMucList", danhMucList);
        model.addAttribute("anhMauList", anhMauList);
        return "admin/them-san-pham";
    }

    @PostMapping("/admin/san-pham/them")
    public String themSanPham(@ModelAttribute("sanPham") SanPham sanPham, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<DanhMuc> danhMucList = danhMucRepository.findAll();
            List<Map<String, String>> anhMauList = Arrays.asList(
                Map.of("url", "/uploads/products/1.jpg", "ten", "Chuột Logitech G502"),
                Map.of("url", "/uploads/products/2.jpg", "ten", "Bàn phím cơ Keychron K2"),
                Map.of("url", "/uploads/products/3.jpg", "ten", "Tai nghe HyperX Cloud II"),
                Map.of("url", "/uploads/products/4.jpg", "ten", "Webcam Logitech C920"),
                Map.of("url", "/uploads/products/5.jpg", "ten", "Chuột Razer DeathAdder V2")
            );
            model.addAttribute("danhMucList", danhMucList);
            model.addAttribute("anhMauList", anhMauList);
            return "admin/them-san-pham";
        }
        sanPhamService.luuSanPham(sanPham);
        return "redirect:/admin/quan-ly-san-pham";
    }
} 