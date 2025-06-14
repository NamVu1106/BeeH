package com.beearena.mapper;

import com.beearena.dto.PhieuXuatDTO;
import com.beearena.entity.PhieuXuat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PhieuXuatMapper {
    
    @Autowired
    private ChiTietPhieuXuatMapper chiTietPhieuXuatMapper;
    
    public PhieuXuatDTO toDTO(PhieuXuat entity) {
        if (entity == null) {
            return null;
        }
        
        PhieuXuatDTO dto = new PhieuXuatDTO();
        dto.setMaPX(entity.getMaPX());
        dto.setNgayXuat(entity.getNgayXuat());
        dto.setTongTien(entity.getTongTien());
        dto.setGhiChu(entity.getGhiChu());
        dto.setTrangThai(entity.isTrangThai());
        
        if (entity.getDanhSachChiTietPhieuXuat() != null) {
            dto.setDanhSachChiTiet(
                entity.getDanhSachChiTietPhieuXuat().stream()
                    .map(chiTietPhieuXuatMapper::toDTO)
                    .collect(Collectors.toList())
            );
        }
        
        return dto;
    }
    
    public PhieuXuat toEntity(PhieuXuatDTO dto) {
        if (dto == null) {
            return null;
        }
        
        PhieuXuat entity = new PhieuXuat();
        entity.setMaPX(dto.getMaPX());
        entity.setNgayXuat(dto.getNgayXuat());
        entity.setTongTien(dto.getTongTien());
        entity.setGhiChu(dto.getGhiChu());
        entity.setTrangThai(dto.isTrangThai());
        
        if (dto.getDanhSachChiTiet() != null) {
            entity.setDanhSachChiTietPhieuXuat(
                dto.getDanhSachChiTiet().stream()
                    .map(chiTietPhieuXuatDTO -> chiTietPhieuXuatMapper.toEntity(chiTietPhieuXuatDTO, entity, null))
                    .collect(Collectors.toList())
            );
        }
        
        return entity;
    }
} 