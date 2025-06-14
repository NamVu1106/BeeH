package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ChiTietDonHangDTO {
    private Long id;
    private Long sanPhamId;
    private String tenSanPham;
    private String hinhAnh;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
} 