package com.beearena.mapper;

import com.beearena.dto.ChiTietDonHangDTO;
import com.beearena.entity.ChiTietDonHang;
import org.springframework.stereotype.Component;

@Component
public class ChiTietDonHangMapper {
    public ChiTietDonHangDTO chuyenDoiSangDTO(ChiTietDonHang chiTietDonHang) {
        if (chiTietDonHang == null) {
            return null;
        }

        ChiTietDonHangDTO dto = new ChiTietDonHangDTO();
        dto.setId(chiTietDonHang.getId());
        dto.setSanPhamId(chiTietDonHang.getSanPham().getMaSP() != null ? chiTietDonHang.getSanPham().getMaSP().longValue() : null);
        dto.setTenSanPham(chiTietDonHang.getSanPham().getTenSP());
        dto.setHinhAnh(chiTietDonHang.getSanPham().getHinhAnh());
        dto.setSoLuong(chiTietDonHang.getSoLuong());
        dto.setDonGia(chiTietDonHang.getDonGia());
        dto.setThanhTien(chiTietDonHang.getThanhTien());

        return dto;
    }

    public ChiTietDonHang chuyenDoiSangEntity(ChiTietDonHangDTO dto) {
        if (dto == null) {
            return null;
        }

        ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
        chiTietDonHang.setId(dto.getId());
        chiTietDonHang.setSoLuong(dto.getSoLuong());
        chiTietDonHang.setDonGia(dto.getDonGia());
        chiTietDonHang.setThanhTien(dto.getThanhTien());

        return chiTietDonHang;
    }
} 