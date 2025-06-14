package com.beearena.mapper;

import com.beearena.dto.LichSuTonKhoDTO;
import com.beearena.entity.LichSuTonKho;
import org.springframework.stereotype.Component;

@Component
public class LichSuTonKhoMapper {
    
    public LichSuTonKhoDTO toDTO(LichSuTonKho entity) {
        if (entity == null) {
            return null;
        }
        
        LichSuTonKhoDTO dto = new LichSuTonKhoDTO();
        dto.setMaLS(entity.getMaLS());
        dto.setMaSP(entity.getSanPham().getMaSP() != null ? entity.getSanPham().getMaSP().longValue() : null);
        dto.setTenSP(entity.getSanPham().getTenSP());
        dto.setLoaiPhatSinh(entity.getLoaiPhatSinh());
        dto.setSoLuong(entity.getSoLuong());
        dto.setTonKhoTruoc(entity.getTonKhoTruoc());
        dto.setTonKhoSau(entity.getTonKhoSau());
        dto.setNgayPhatSinh(entity.getNgayPhatSinh());
        dto.setGhiChu(entity.getGhiChu());
        
        return dto;
    }
    
    public LichSuTonKho toEntity(LichSuTonKhoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        LichSuTonKho entity = new LichSuTonKho();
        entity.setMaLS(dto.getMaLS());
        entity.setLoaiPhatSinh(dto.getLoaiPhatSinh());
        entity.setSoLuong(dto.getSoLuong());
        entity.setTonKhoTruoc(dto.getTonKhoTruoc());
        entity.setTonKhoSau(dto.getTonKhoSau());
        entity.setNgayPhatSinh(dto.getNgayPhatSinh());
        entity.setGhiChu(dto.getGhiChu());
        
        return entity;
    }
} 