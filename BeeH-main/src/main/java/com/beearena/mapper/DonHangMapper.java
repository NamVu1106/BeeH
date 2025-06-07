package com.beearena.mapper;

import com.beearena.dto.DonHangDTO;

import com.beearena.entity.DonHang;

import com.beearena.enums.TrangThaiDonHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DonHangMapper {
    @Autowired
    private ChiTietDonHangMapper chiTietDonHangMapper;

    public DonHangDTO chuyenDoiSangDTO(DonHang donHang) {
        if (donHang == null) {
            return null;
        }

        DonHangDTO dto = new DonHangDTO();
        dto.setId(donHang.getId());
        dto.setNguoiDungId(donHang.getNguoiDung().getId());
        dto.setHoTenNguoiDung(donHang.getNguoiDung().getHoTen());
        dto.setDiaChiGiaoHang(donHang.getDiaChiGiaoHang());
        dto.setSoDienThoaiGiaoHang(donHang.getSoDienThoai());
        dto.setTongTien(donHang.getTongTien());
        dto.setTrangThai(donHang.getTrangThai().toString());
        dto.setNgayTao(donHang.getNgayTao());
        dto.setNgayCapNhat(donHang.getNgayCapNhat());

        if (donHang.getDanhSachChiTiet() != null) {
            dto.setChiTietDonHang(donHang.getDanhSachChiTiet().stream()
                    .map(chiTietDonHangMapper::chuyenDoiSangDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public DonHang chuyenDoiSangEntity(DonHangDTO dto) {
        if (dto == null) {
            return null;
        }

        DonHang donHang = new DonHang();
        donHang.setId(dto.getId());
        donHang.setDiaChiGiaoHang(dto.getDiaChiGiaoHang());
        donHang.setSoDienThoai(dto.getSoDienThoaiGiaoHang());
        donHang.setTongTien(dto.getTongTien());
        donHang.setTrangThai(TrangThaiDonHang.valueOf(dto.getTrangThai()));
        donHang.setNgayTao(dto.getNgayTao());
        donHang.setNgayCapNhat(dto.getNgayCapNhat());

        return donHang;
    }
} 