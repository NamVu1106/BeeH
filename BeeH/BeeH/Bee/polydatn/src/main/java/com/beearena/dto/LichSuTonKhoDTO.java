package com.beearena.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LichSuTonKhoDTO {
    private Long maLS;
    private Long maSP;
    private String tenSP;
    private String loaiPhatSinh;
    private Integer soLuong;
    private Integer tonKhoTruoc;
    private Integer tonKhoSau;
    private LocalDateTime ngayPhatSinh;
    private String ghiChu;
} 