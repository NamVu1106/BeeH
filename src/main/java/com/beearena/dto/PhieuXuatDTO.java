package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PhieuXuatDTO {
    private Long maPX;
    private LocalDateTime ngayXuat;
    private BigDecimal tongTien;
    private String ghiChu;
    private boolean trangThai;
    private List<ChiTietPhieuXuatDTO> danhSachChiTiet;
} 