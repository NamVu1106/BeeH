package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SanPhamDTO {
    private Long id;
    private String ten;
    private String moTa;
    private BigDecimal gia;
    private String hinhAnh;
    private Integer soLuong;
    private String thuongHieu;
    private Long danhMucId;
    private String tenDanhMuc;
    private boolean conHang;
    private String mauSac;
} 