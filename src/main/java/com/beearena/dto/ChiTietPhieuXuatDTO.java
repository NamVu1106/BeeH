package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ChiTietPhieuXuatDTO {
    private Long maCTPX;
    private Long maSP;
    private String tenSP;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
    private Long maPX;
} 