package com.beearena.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NhaCungCapDTO {
    private Long maNCC;
    private String tenNCC;
    private String diaChi;
    private String sdt;
    private String email;
    private LocalDateTime ngayTao;
    private boolean trangThai;
} 