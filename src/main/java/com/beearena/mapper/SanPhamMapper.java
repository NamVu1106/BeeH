package com.beearena.mapper;

import com.beearena.dto.SanPhamDTO;
import com.beearena.entity.SanPham;
import org.springframework.stereotype.Component;

@Component
public class SanPhamMapper {
    public SanPhamDTO toDTO(SanPham sp) {
        if (sp == null) return null;
        SanPhamDTO dto = new SanPhamDTO();
        dto.setId(sp.getMaSP());
        dto.setTen(sp.getTenSP());
        dto.setGia(sp.getGia());
        dto.setSoLuong(sp.getSoLuong());
        dto.setHinhAnh(sp.getHinhAnh());
        // Xóa hoặc comment dòng dưới vì entity SanPham không còn trường danhMuc
        // dto.setTenDanhMuc(sp.getDanhMuc() != null ? sp.getDanhMuc().getTenDM() : null);
        return dto;
    }
} 