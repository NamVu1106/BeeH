package com.beearena.mapper;

import com.beearena.dto.SanPhamDTO;
import com.beearena.entity.SanPham;
import org.springframework.stereotype.Component;

@Component
public class SanPhamMapper {
    public SanPhamDTO chuyenDoiSangDTO(SanPham sanPham) {
        if (sanPham == null) {
            return null;
        }

        SanPhamDTO dto = new SanPhamDTO();
        dto.setId(sanPham.getMaSP() != null ? sanPham.getMaSP().longValue() : null);
        dto.setTen(sanPham.getTenSP());
        dto.setMoTa(sanPham.getMoTa());
        dto.setGia(sanPham.getGia());
        dto.setHinhAnh(sanPham.getHinhAnh());
        dto.setSoLuong(sanPham.getSoLuong());
        dto.setMauSac(sanPham.getMauSac());

        return dto;
    }

    public SanPham chuyenDoiSangEntity(SanPhamDTO dto) {
        if (dto == null) {
            return null;
        }

        SanPham sanPham = new SanPham();
        sanPham.setMaSP(dto.getId() != null ? dto.getId().intValue() : null);
        sanPham.setTenSP(dto.getTen());
        sanPham.setMoTa(dto.getMoTa());
        sanPham.setGia(dto.getGia());
        sanPham.setHinhAnh(dto.getHinhAnh());
        sanPham.setSoLuong(dto.getSoLuong());
        sanPham.setMauSac(dto.getMauSac());

        return sanPham;
    }
} 