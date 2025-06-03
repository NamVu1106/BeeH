package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PhieuNhapDTO {
    private Long maPN;
    private Long maNCC;
    private String tenNCC;
    private LocalDateTime ngayNhap;
    private BigDecimal tongTien;
    private String ghiChu;
    private boolean trangThai;
    private List<ChiTietPhieuNhapDTO> danhSachChiTiet;
} 