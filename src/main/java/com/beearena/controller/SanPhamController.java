package com.beearena.controller;

import com.beearena.entity.SanPham;
import com.beearena.service.SanPhamService;
import com.beearena.dto.SanPhamDTO;
import com.beearena.mapper.SanPhamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@CrossOrigin
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private SanPhamMapper sanPhamMapper;

    @GetMapping
    public List<SanPham> layTatCaSanPham() {
        return sanPhamService.layTatCaSanPham(org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanPham> laySanPhamTheoId(@PathVariable Long id) {
        return sanPhamService.laySanPhamTheoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tim-kiem")
    public List<SanPham> timSanPhamTheoTen(@RequestParam String ten) {
        return sanPhamService.timSanPhamTheoTen(ten);
    }

  

    @GetMapping("/dang-hoat-dong")
    public List<SanPham> laySanPhamDangHoatDong() {
        return sanPhamService.laySanPhamDangHoatDong();
    }

    @PostMapping
    public SanPham themSanPham(@RequestBody SanPham sanPham) {
        return sanPhamService.luuSanPham(sanPham);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanPham> capNhatSanPham(@PathVariable Long id, @RequestBody SanPham sanPham) {
        return sanPhamService.laySanPhamTheoId(id)
                .map(existingSanPham -> {
                    sanPham.setMaSP(id != null ? id.intValue() : null);
                    return ResponseEntity.ok(sanPhamService.luuSanPham(sanPham));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaSanPham(@PathVariable Long id) {
        return sanPhamService.laySanPhamTheoId(id)
                .map(sanPham -> {
                    sanPhamService.xoaSanPham(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    public Page<SanPham> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return sanPhamService.layTatCaSanPham(PageRequest.of(page, size));
    }

    @GetMapping("/tim-kiem-nang-cao")
    public Page<SanPham> timKiemNangCao(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer maDM,
            @RequestParam(required = false) BigDecimal giaTu,
            @RequestParam(required = false) BigDecimal giaDen,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "maSP,desc") String[] sort
    ) {
        // Xử lý sort
        org.springframework.data.domain.Sort sortObj = org.springframework.data.domain.Sort.by("maSP").descending();
        if (sort != null && sort.length > 0) {
            String[] sortArr = sort[0].split(",");
            if (sortArr.length == 2) {
                sortObj = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.fromString(sortArr[1]), sortArr[0]);
            }
        }
        return sanPhamService.timKiemNangCao(keyword, maDM, giaTu, giaDen, null, null, org.springframework.data.domain.PageRequest.of(page, size, sortObj));
    }

    @GetMapping("/products-dto")
    public Page<SanPhamDTO> getAllProductsDTO(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return sanPhamService.layTatCaSanPham(PageRequest.of(page, size)).map(sanPhamMapper::toDTO);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<SanPhamDTO> laySanPhamDTOTheoId(@PathVariable Long id) {
        return sanPhamService.laySanPhamTheoId(id)
                .map(sanPham -> ResponseEntity.ok(sanPhamMapper.toDTO(sanPham)))
                .orElse(ResponseEntity.notFound().build());
    }
} 