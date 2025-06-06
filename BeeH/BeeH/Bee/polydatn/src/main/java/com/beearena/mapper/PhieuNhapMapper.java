package com.beearena.mapper;

import com.beearena.dto.PhieuNhapDTO;
import com.beearena.entity.PhieuNhap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PhieuNhapMapper {
    
    @Autowired
    private ChiTietPhieuNhapMapper chiTietPhieuNhapMapper;
    
    public PhieuNhapDTO toDTO(PhieuNhap entity) {
        if (entity == null) {
            return null;
        }
        
        PhieuNhapDTO dto = new PhieuNhapDTO();
        dto.setMaPN(entity.getMaPN());
        dto.setMaNCC(entity.getNhaCungCap().getMaNCC());
        dto.setTenNCC(entity.getNhaCungCap().getTenNCC());
        dto.setNgayNhap(entity.getNgayNhap());
        dto.setTongTien(entity.getTongTien());
        dto.setGhiChu(entity.getGhiChu());
        dto.setTrangThai(entity.isTrangThai());
        
        if (entity.getDanhSachChiTietPhieuNhap() != null) {
            dto.setDanhSachChiTiet(
                entity.getDanhSachChiTietPhieuNhap().stream()
                    .map(chiTietPhieuNhapMapper::toDTO)
                    .collect(Collectors.toList())
            );
        }
        
        return dto;
    }
    
    public PhieuNhap toEntity(PhieuNhapDTO dto) {
        if (dto == null) {
            return null;
        }
        
        PhieuNhap entity = new PhieuNhap();
        entity.setMaPN(dto.getMaPN());
        entity.setNgayNhap(dto.getNgayNhap());
        entity.setTongTien(dto.getTongTien());
        entity.setGhiChu(dto.getGhiChu());
        entity.setTrangThai(dto.isTrangThai());
        
        if (dto.getDanhSachChiTiet() != null) {
            entity.setDanhSachChiTietPhieuNhap(
                dto.getDanhSachChiTiet().stream()
                    .map(chiTietPhieuNhapMapper::toEntity)
                    .collect(Collectors.toList())
            );
        }
        
        return entity;
    }
} 