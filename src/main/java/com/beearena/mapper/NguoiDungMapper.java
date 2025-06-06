package com.beearena.mapper;

import com.beearena.dto.NguoiDungDTO;
import com.beearena.entity.NguoiDung;
import com.beearena.entity.VaiTro;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungMapper {
    public NguoiDungDTO chuyenDoiSangDTO(NguoiDung nguoiDung) {
        if (nguoiDung == null) {
            return null;
        }

        NguoiDungDTO dto = new NguoiDungDTO();
        dto.setId(nguoiDung.getId());
        dto.setHoTen(nguoiDung.getHoTen());
        dto.setEmail(nguoiDung.getEmail());
        dto.setSoDienThoai(nguoiDung.getSoDienThoai());
        dto.setDiaChi(nguoiDung.getDiaChi());
        dto.setVaiTro(nguoiDung.getVaiTro().toString());

        return dto;
    }

    public NguoiDung chuyenDoiSangEntity(NguoiDungDTO dto) {
        if (dto == null) {
            return null;
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(dto.getId());
        nguoiDung.setHoTen(dto.getHoTen());
        nguoiDung.setEmail(dto.getEmail());
        nguoiDung.setSoDienThoai(dto.getSoDienThoai());
        nguoiDung.setDiaChi(dto.getDiaChi());
        nguoiDung.setVaiTro(VaiTro.valueOf(dto.getVaiTro()));

        return nguoiDung;
    }
} 