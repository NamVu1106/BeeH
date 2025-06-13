package com.beearena.mapper;

import com.beearena.dto.ChiTietPhieuNhapDTO;
import com.beearena.entity.ChiTietPhieuNhap;
import org.springframework.stereotype.Component;

@Component
public class ChiTietPhieuNhapMapper {
    
    public ChiTietPhieuNhapDTO toDTO(ChiTietPhieuNhap entity) {
        if (entity == null) {
            return null;
        }
        
        ChiTietPhieuNhapDTO dto = new ChiTietPhieuNhapDTO();
        dto.setMaCTPN(entity.getMaCTPN());
        dto.setMaSP(entity.getSanPham().getMaSP() != null ? entity.getSanPham().getMaSP().longValue() : null);
        dto.setTenSP(entity.getSanPham().getTenSP());
        dto.setSoLuong(entity.getSoLuong());
        dto.setDonGia(entity.getDonGia());
        dto.setThanhTien(entity.getThanhTien());
        
        return dto;
    }
    
    public ChiTietPhieuNhap toEntity(ChiTietPhieuNhapDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ChiTietPhieuNhap entity = new ChiTietPhieuNhap();
        entity.setMaCTPN(dto.getMaCTPN());
        entity.setSoLuong(dto.getSoLuong());
        entity.setDonGia(dto.getDonGia());
        entity.setThanhTien(dto.getThanhTien());
        
        return entity;
    }
} 