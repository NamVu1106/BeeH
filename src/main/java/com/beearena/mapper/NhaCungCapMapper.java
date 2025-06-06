package com.beearena.mapper;

import com.beearena.dto.NhaCungCapDTO;
import com.beearena.entity.NhaCungCap;
import org.springframework.stereotype.Component;

@Component
public class NhaCungCapMapper {
    
    public NhaCungCapDTO toDTO(NhaCungCap entity) {
        if (entity == null) {
            return null;
        }
        
        NhaCungCapDTO dto = new NhaCungCapDTO();
        dto.setMaNCC(entity.getMaNCC());
        dto.setTenNCC(entity.getTenNCC());
        dto.setDiaChi(entity.getDiaChi());
        dto.setSdt(entity.getSdt());
        dto.setEmail(entity.getEmail());
        dto.setNgayTao(entity.getNgayTao());
        dto.setTrangThai(entity.isTrangThai());
        
        return dto;
    }
    
    public NhaCungCap toEntity(NhaCungCapDTO dto) {
        if (dto == null) {
            return null;
        }
        
        NhaCungCap entity = new NhaCungCap();
        entity.setMaNCC(dto.getMaNCC());
        entity.setTenNCC(dto.getTenNCC());
        entity.setDiaChi(dto.getDiaChi());
        entity.setSdt(dto.getSdt());
        entity.setEmail(dto.getEmail());
        entity.setNgayTao(dto.getNgayTao());
        entity.setTrangThai(dto.isTrangThai());
        
        return entity;
    }
} 