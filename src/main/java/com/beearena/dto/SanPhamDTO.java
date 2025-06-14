package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SanPhamDTO {
    private Integer id;
    private String ten;
    private BigDecimal gia;
    private Integer soLuong;
    private String hinhAnh;
    private String tenDanhMuc;
    private List<String> phienBanList;
    private List<String> mauSacList;
} 