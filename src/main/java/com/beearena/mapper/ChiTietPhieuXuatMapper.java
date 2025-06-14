package com.beearena.mapper;

import com.beearena.dto.ChiTietPhieuXuatDTO;
import com.beearena.entity.ChiTietPhieuXuat;
import com.beearena.entity.PhieuXuat;
import com.beearena.entity.SanPham;
import org.springframework.stereotype.Component;

@Component
public class ChiTietPhieuXuatMapper {
    
    public ChiTietPhieuXuatDTO toDTO(ChiTietPhieuXuat entity) {
        if (entity == null) return null;
        
        ChiTietPhieuXuatDTO dto = new ChiTietPhieuXuatDTO();
        dto.setMaCTPX(entity.getMaCTPX());
        dto.setMaPX(entity.getPhieuXuat() != null ? (entity.getPhieuXuat().getMaPX() != null ? entity.getPhieuXuat().getMaPX().longValue() : null) : null);
        dto.setMaSP(entity.getSanPham() != null ? (entity.getSanPham().getMaSP() != null ? entity.getSanPham().getMaSP().longValue() : null) : null);
        dto.setTenSP(entity.getSanPham().getTenSP());
        dto.setSoLuong(entity.getSoLuong());
        dto.setDonGia(entity.getDonGia());
        dto.setThanhTien(entity.getThanhTien());
        
        return dto;
    }
    
    public ChiTietPhieuXuat toEntity(ChiTietPhieuXuatDTO dto, PhieuXuat phieuXuat, SanPham sanPham) {
        if (dto == null) return null;
        
        ChiTietPhieuXuat entity = new ChiTietPhieuXuat();
        entity.setMaCTPX(dto.getMaCTPX());
        entity.setPhieuXuat(phieuXuat);
        entity.setSanPham(sanPham);
        entity.setSoLuong(dto.getSoLuong());
        entity.setDonGia(dto.getDonGia());
        entity.setThanhTien(dto.getThanhTien());
        
        return entity;
    }
} 